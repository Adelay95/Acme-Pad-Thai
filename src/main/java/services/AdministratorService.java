package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Category;
import domain.Contest;
import domain.Cook;
import domain.Folder;
import domain.MasterClass;
import domain.Message;
import domain.Priority;
import domain.Recipe;
import domain.SocialIdentity;
import domain.Spam;
import domain.Sponsor;
import domain.User;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;
	@Autowired
	private MessageService messageService;
	@Autowired
	private SpamService spamService;
	@Autowired
	private ContestService contestService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private CookService cookService;
	@Autowired
	private MasterClassService masterClassService;
        @Autowired
	private UserService userService;
	@Autowired
	private SponsorService sponsorService;
	@Autowired
	private MonthlyBillService monthlyBillsService;

	public AdministratorService() {
		super();
	}

	public Administrator create() {
		Administrator res;
		Collection<Folder> folders = new ArrayList<Folder>();
		Collection<SocialIdentity> socialIdentities = new HashSet<SocialIdentity>();
		Collection<Message> messageSent = new HashSet<Message>();
		Collection<Message> messageReceived = new HashSet<Message>();
		res = new Administrator();
		res.setSocialIdentities(socialIdentities);
		res.setMessageReceived(messageReceived);
		res.setMessageSent(messageSent);

		res.setFolders(folders);
		return res;
	}

	public Administrator save(Administrator administrator) {
		Assert.notNull(administrator);
		Administrator res;
		res = administratorRepository.save(administrator);
		if (res.getFolders().isEmpty()) {
			Collection<Folder> folder = actorService.createFolderss(res);
			res.setFolders(folder);
			res = administratorRepository.save(res);
		}
		return res;
	}

	public void delete(Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);
		administratorRepository.delete(administrator);
	}

	public Collection<Administrator> allAdministrator() {
		Collection<Administrator> res;
		res = administratorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Administrator findOne(int id) {
		Administrator res;
		res = administratorRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Administrator findByPrincipal() {
		Administrator res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = findByUserAccount(userAccount);
		Assert.notNull(res);
		return res;
	}

	private Administrator findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Administrator res;
		res = administratorRepository.findByUserAccountId(userAccount.getId());
		return res;

	}

	public Spam crearSpam() {
		Spam res;
		Spam saved;
		res = spamService.create();
		saved = spamService.save(res);
		return saved;
	}

	public Contest crearContest(Contest res) {
//		Contest res;
		Contest saved;
//		res = contestService.create();
		saved = contestService.save(res);
		return saved;
	}
    
	public Contest modificarContest(Contest contest1) {
		Assert.notNull(contest1);
		Assert.notNull(contest1.getOpeningTime());
		Assert.notNull(contest1.getClosingTime());
		Contest contest=contestService.findOne(contest1.getId());
		Date now=new Date();
		if(contest.getQualifiedRecipes().isEmpty()){
			if(now.after(contest.getOpeningTime()) && now.before(contest.getClosingTime())){
				Assert.isTrue(contest1.getClosingTime().after(now));
				contest.setClosingTime(contest1.getClosingTime());
			}else if(now.before(contest.getOpeningTime())){
				
				contest.setOpeningTime(contest1.getOpeningTime());
				contest.setTitle(contest1.getTitle());
				contest.setClosingTime(contest1.getClosingTime());
			}
		}
		return contestService.save(contest);

	}

	public void borrarContest(int id) {
		Contest uno;
		uno = contestService.findOne(id);
		Assert.notNull(uno);
		Assert.isTrue(uno.getQualifiedRecipes().isEmpty());
		contestService.delete(uno);
	}

	public Collection<Contest> contests() {
		return contestService.allContest();
	}

	// Castell

	public Collection<Category> categories() {
		return categoryService.allCategory();
	}

	public Category crearCategory(Category res) {

		Category saved;
		Assert.notNull(res);
		saved = categoryService.save(res);
		return saved;
	}

	public void borrarCategory(Category category) {
		Assert.notNull(category);
		Assert.isTrue(category.getRecipes().isEmpty());
		categoryService.delete(category);
	}

		public void promote(int masterClass) {
			MasterClass m;
			Administrator a;
			m = masterClassService.findOne(masterClass);
			Assert.notNull(m);
			a = findByPrincipal();
			Assert.notNull(a);
			m.setPromote(true);
			masterClassService.save(m);

		}

		public void depromote(int masterClass) {
			MasterClass m;
			Administrator a;
			m = masterClassService.findOne(masterClass);
			Assert.notNull(m);
			a = findByPrincipal();
			Assert.notNull(a);
			m.setPromote(false);
			masterClassService.save(m);

		}

		public void calcularGanador(int id) {
			Contest c;
			c = contestService.findOne(id);
			Assert.notNull(c);

			Collection<Recipe> recetas = c.getQualifiedRecipes();
			Collection<Recipe> nuevasRecetasGanadas=new HashSet<Recipe>();
			int acum = 0;
			Recipe max1 = null;
			Recipe max2 = null;
			Recipe max3 = null;
			for (Recipe r : recetas) {
				acum = r.getTastes().size();
				if (max1 == null) {
					max1 = r;
				} else if (max2 == null) {
					max2 = r;
				} else if (max3 == null) {
					max3 = r;
				} else if (acum >= max1.getTastes().size()) {
					max1 = r;
				} else if (acum >= max2.getTastes().size()) {
					max2 = r;
				} else if (acum >= max3.getTastes().size()) {
					max3 = r;
				}
				acum = 0;
			}
			if (max1 != null)
				nuevasRecetasGanadas.add(max1);
			if (max2 != null)
				nuevasRecetasGanadas.add(max2);
			if (max3 != null) {
				nuevasRecetasGanadas.add(max3);
			}
			c.setWinnerRecipes(nuevasRecetasGanadas);
			contestService.save(c);
			for (Recipe p : c.getWinnerRecipes()) {
				   p.setWinnerContests(c);
				   recipeService.saveWinnerRecipe(p);

				  }

		}
		
		public Cook createCook(){
			Cook res=cookService.create();
			return cookService.save(res);
		}
		
		public void messageMoroso(Collection<Sponsor> sponsors){
			Administrator me=findByPrincipal();
			for(Sponsor sponsor:sponsors){
			Message message = messageService.create(me, sponsor);
			message.setBody("Saludos, soy el administrador "+ me.getName()+" "+ me.getSurname()+"" +
					" y le comunico que uster, "+ sponsor.getName() +" " +sponsor.getSurname() +" tiene unas facturas pendientes desde hace un mes " +
							".Le ruego que abone sus deudas lo antes posible, un saludo.");
			message.setSubject("Facturas impagadas");
			message.setPriority(Priority.HIGH);
			actorService.sendMessage(message);
			}
		}

	//CASTELL
		
		public Collection<String> rankingCompanyName(){
			return sponsorService.getRankingCompanyName2();
		}
		
		public Collection<String> rankingMonthlyBills(){
			return sponsorService.getRankingMonthlyBills2();
		}
		
		public Double AvgPaidBills(){
			return monthlyBillsService.AvgPaidBills();
		}
		public Double AvgUnpaidBills(){
			return monthlyBillsService.AvgUnpaidBills();
		}
		public Double DevPaidBills(){
			return monthlyBillsService.DevPaidBills();
		}
		public Double DevUnpaidBills(){
			return monthlyBillsService.DevUnpaidBills();
		}
		public Collection<Sponsor> sponsorNoActive(){
			return sponsorService.getSponsorNoActive2();
		}
		
		public Collection<String> campaignNoActive(){
			return monthlyBillsService.campaignNoActive();
		}
		
		public Collection<String> campaign90(){
			return monthlyBillsService.campaign90();
		}
		
		public Integer minMasterClassCook(){
			return cookService.minMasterClassCook();
		}
		public Integer maxMasterClassCook(){
			return cookService.maxMasterClassCook();
		}
		public Double avgMasterClassCook(){
			return cookService.avgMasterClassCook();
		}
		public Double devMasterClassCook(){
			return cookService.devMasterClassCook();
		}
		
		public Double avgPresentationMasterClass(){
			return masterClassService.avgPresentationMasterClass();
		}
		public Double avgTextMasterClass(){
			return masterClassService.avgTextMasterClass();
		}
		public Double avgVideosMasterClass(){
			return masterClassService.avgVideosMasterClass();
		}
		
		
		public Integer numMasterClassPromoted(){
			return masterClassService.masterClassPromoted();
		}
		
		public Collection<Cook> rankingMasterClassPromeoted(){
			return cookService.rankingMasterClassPromeoted();
		}
		
		public Double avgCookNumDemoted(){
			return cookService.avgCookNumDemoted();
		}
		public Double avgCookNumPromoted(){
			return cookService.avgCookNumPromoted();
		}
		public Integer minRecipesUser() {
			return userService.minRecipesUser();
		}
		public Double avgRecipesUser() {
			return userService.avgRecipesUser();
		}
		public Integer maxRecipesUser() {
			return userService.maxRecipesUser();
		}

		public Collection<User> userMaxRecipes() {
			return userService.userMaxRecipes();
		}
		
		public Collection<User> popularityListing(){
			return userService.popularityListing();
		}
		
		public Collection<User> tastesUsers(){
			return userService.tastesUsers();
		}
		
		public Integer maxRecipesContests(){
			return contestService.maxRecipesContests();
		}
		public Double avgRecipesContests(){
			return contestService.avgRecipesContests();
		}
		public Integer minRecipesContests(){
			return contestService.minRecipesContests();
		}

		public Collection<Contest> getcontestMaxRecipesQualificated(){
			return contestService.getcontestMaxRecipesQualificated();
		}
		
		public Double avgQuantitiesRecipes(){
			return recipeService.avgQuantitiesRecipes();
		}
		public Double devQuantitiesRecipes(){
			return recipeService.devQuantitiesRecipes();
		}
		public Double avgStepsRecipes(){
			return recipeService.avgStepsRecipes();
		}
		public Double devStepsRecipes(){
			return recipeService.devStepsRecipes();
		}
		
		public Integer maxCampaignSponsor() {
			return sponsorService.maxCampaignSponsor();
		}
		public Integer minCampaignSponsor() {
			return sponsorService.minCampaignSponsor();
		}
		public Double avgCampaignSponsor() {
			return sponsorService.avgCampaignSponsor();
		}
		public Integer maxCampaignSponsorActives() {
			return sponsorService.maxCampaignSponsorActives();
		}
		public Integer minCampaignSponsorActives() {
			return sponsorService.minCampaignSponsorActives();
		}
		public Double avgCampaignSponsorActives() {
			return sponsorService.avgCampaignSponsorActives();
		}

	}
