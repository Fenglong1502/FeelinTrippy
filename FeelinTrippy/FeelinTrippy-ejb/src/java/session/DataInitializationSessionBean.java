/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.BookedActivity;
import entity.Customer;
import entity.Prize;
import entity.SavedTrip;
import entity.TrippyEventItem;
import entity.TrippyEventType;
import error.CustomerAddBookedActivityException;
import error.CustomerAddSavedTripException;
import error.NoResultException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dk349
 */
@Singleton
@LocalBean
@Startup
public class DataInitializationSessionBean {

    @PersistenceContext(unitName = "FeelinTrippy-ejbPU")
    private EntityManager em;

    @EJB
    private TrippyEventSessionLocal trippyEventSessionLocal;
    @EJB
    private CustomerSessionLocal customerSessionLocal;
    @EJB
    private TrippyEventTypeSessionLocal trippyEventTypeSessionLocal;
    @EJB
    private SavedTripSessionLocal savedTripSessionLocal;
    @EJB
    private PrizeSessionLocal prizeSessionLocal;
    @EJB
    private BookedActivitySessionLocal bookedActivitySessionLocal;

    @PostConstruct
    public void postConstruct() {
        if (em.find(Customer.class, 1l) == null) {
            initializeData();
        }
    }

    public DataInitializationSessionBean() {
    }

    private void initializeData() {
        //Initialise new user
        Customer admin = new Customer("admin", encryptPassword("admin"), "Administrator", "", true, (byte) 1, "", "admin", 0, true);
        Customer c1 = new Customer("user1", encryptPassword("password"), "Chee Siang", "Ng", true, (byte) 2, "98261304", "E0032155@u.nus.edu", 5530, false);
        Customer c2 = new Customer("user2", encryptPassword("password"), "Feng Long", "Lim", true, (byte) 2, "91234567", "zell1502@hotmail.com", 1254370, false);
        Customer c3 = new Customer("user3", encryptPassword("password"), "Dian Cong", "Lim", true, (byte) 2, "94308808", "congx2@hotmail.com", 5450, false);
        Customer c4 = new Customer("user4", encryptPassword("password"), "Jinhao", "Wu", true, (byte) 2, "81985902", "e0053071@u.nus.edu", 4870, false);
        Customer c5 = new Customer("user5", encryptPassword("password"), "Derek", "Chan", true, (byte) 2, "82005886", "derekchan@u.nus.edu", 8780, false);
        Customer c6 = new Customer("user6", encryptPassword("123"), "Meng Chuan", "Gai", true, (byte) 2, "90025633", "gaimc24@hotmail.com", 8710, false);

        customerSessionLocal.createCustomer(admin);
        customerSessionLocal.createCustomer(c1);
        customerSessionLocal.createCustomer(c2);
        customerSessionLocal.createCustomer(c3);
        customerSessionLocal.createCustomer(c4);
        customerSessionLocal.createCustomer(c5);
        customerSessionLocal.createCustomer(c6);

        // Declaring the variables to be used in initialising data
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        ArrayList eventImages = new ArrayList();
        ArrayList eventTypes = new ArrayList();

        // Initialising the event types
        TrippyEventType animals_and_wildlife = new TrippyEventType("Animals and Wildlife", false);
        TrippyEventType adventure = new TrippyEventType("Adventure", false);
        TrippyEventType music_and_night_life = new TrippyEventType("Music and Night Life", false);
        TrippyEventType art_and_culture = new TrippyEventType("Art and Culture", false);
        TrippyEventType foodie = new TrippyEventType("Foodie", false);

        trippyEventTypeSessionLocal.createTrippyType(animals_and_wildlife);
        trippyEventTypeSessionLocal.createTrippyType(adventure);
        trippyEventTypeSessionLocal.createTrippyType(music_and_night_life);
        trippyEventTypeSessionLocal.createTrippyType(art_and_culture);
        trippyEventTypeSessionLocal.createTrippyType(foodie);

        animals_and_wildlife = trippyEventTypeSessionLocal.getAllTripType().get(0);
        adventure = trippyEventTypeSessionLocal.getAllTripType().get(1);
        music_and_night_life = trippyEventTypeSessionLocal.getAllTripType().get(2);
        art_and_culture = trippyEventTypeSessionLocal.getAllTripType().get(3);
        foodie = trippyEventTypeSessionLocal.getAllTripType().get(4);

        // Initialising the events for animals and wildlife   
        startDate.set(2018, Calendar.FEBRUARY, 14);
        endDate.set(2019, Calendar.JANUARY, 13);
        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem1 = new TrippyEventItem();
        trippyEventItem1.setEventName("All About Dogs Exhibition");
        trippyEventItem1.setEventDescription("Originating from wolf ancestors, this amazing animal is more than a pet. Using its keen sense of smell and hearing, it provides protection, security checks at immigration control, search and rescue, guide and assistance, and herding for farm animals. The All About Dogs exhibition explores the close bond between dogs and humans, earning it the title – man’s best friend, and will also sniff out what the 2018 Earth Dog year has in store for all the 12 animal zodiac signs.");
        trippyEventItem1.setPoint(0L);
        trippyEventItem1.setStartDate(startDate.getTime());
        trippyEventItem1.setEndDate(endDate.getTime());
        trippyEventItem1.setPrice(0.0);
        trippyEventItem1.setEventImage("trippyEventItem1.jpg");
        trippyEventItem1.setAddress("23-B Coleman St, Singapore 179807");
        trippyEventItem1.setEventType(eventTypes);
        trippyEventItem1.setSoftDelete(false);
        trippyEventItem1.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem2 = new TrippyEventItem();
        trippyEventItem2.setEventName("The Animal Resort");
        trippyEventItem2.setEventDescription("Hidden among the farmways at Seletar is The Animal Resort. Step inside this rustic getaway and be transported into a different time. A gaggle of geese wander around freely while chickens peck away at the ground. ");
        trippyEventItem2.setPoint(0L);
        trippyEventItem2.setPrice(0.0);
        trippyEventItem2.setEventImage("trippyEventItem2.jpg");
        trippyEventItem2.setAddress("81 Seletar West Farmway 5, Singapore 798058");
        trippyEventItem2.setEventType(eventTypes);
        trippyEventItem2.setSoftDelete(false);
        trippyEventItem2.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem2);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem73 = new TrippyEventItem();
        trippyEventItem73.setEventName("Farmart Centre Singapore");
        trippyEventItem73.setEventDescription("Surrounded by lush greenery & rustic charm, Farmart Centre is the ideal location for relaxation and casual dining.");
        trippyEventItem73.setPoint(0L);
        trippyEventItem73.setPrice(0.0);
        trippyEventItem73.setEventImage("trippyEventItem73.jpg");
        trippyEventItem73.setAddress("67 Sungei Tengah Road, Singapore 699008");
        trippyEventItem73.setEventType(eventTypes);
        trippyEventItem73.setSoftDelete(false);
        trippyEventItem73.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem73);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem74 = new TrippyEventItem();
        trippyEventItem74.setEventName("The Live Turtle & Tortoise Museum");
        trippyEventItem74.setEventDescription("Found within Chinese Garden at Jurong, the Live Turtle and Tortoise Museum is a mini, private ‘zoo’ dedicated to turtles and tortoises. Its walled garden is home to more than 1,600 live animals and it is listed in the Guinness Book of World Records as having the largest collection of tortoise and turtle items.");
        trippyEventItem74.setPoint(0L);
        trippyEventItem74.setPrice(0.0);
        trippyEventItem74.setEventImage("trippyEventItem74.jpg");
        trippyEventItem74.setAddress("Chinese Garden");
        trippyEventItem74.setEventType(eventTypes);
        trippyEventItem74.setSoftDelete(false);
        trippyEventItem74.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem74);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem75 = new TrippyEventItem();
        trippyEventItem75.setEventName("S.E.A Aquarium");
        trippyEventItem75.setEventDescription("Enter and explore the marine realm of S.E.A. Aquarium, home to more than 100,000 marine animals of over 1,000 species, across 50 different habitats, each one as fascinating as the next. It's a marine life experience you won’t forget.");
        trippyEventItem75.setPoint(0L);
        trippyEventItem75.setPrice(34.0);
        trippyEventItem75.setEventImage("trippyEventItem75.jpg");
        trippyEventItem75.setAddress("8 Sentosa Gateway, Sentosa Island, Singapore 098269");
        trippyEventItem75.setEventType(eventTypes);
        trippyEventItem75.setSoftDelete(false);
        trippyEventItem75.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem75);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem76 = new TrippyEventItem();
        trippyEventItem76.setEventName("Happenstance Cafe");
        trippyEventItem76.setEventDescription("Happenstance Cafe is a cosy cafe at which you can bond with your fur-kid. It's one of the cafes designed to accommodate pets; there's a special pasta menu just for your dogs, which offers customisable pasta (starts from $6.50) for them.");
        trippyEventItem76.setPoint(0L);
        trippyEventItem76.setPrice(15.0);
        trippyEventItem76.setEventImage("trippyEventItem76.jpg");
        trippyEventItem76.setAddress("35 Opal Cres, Singapore 328425");
        trippyEventItem76.setEventType(eventTypes);
        trippyEventItem76.setSoftDelete(false);
        trippyEventItem76.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem76);

        startDate.set(2018, Calendar.JULY, 1);
        endDate.set(2018, Calendar.DECEMBER, 31);
        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem3 = new TrippyEventItem();
        trippyEventItem3.setEventName("Luminus Zoo");
        trippyEventItem3.setEventDescription("A lush and luminous universe awaits you at Rainforest Lumina, a multimedia night walk on the wild side.");
        trippyEventItem3.setPoint(0L);
        trippyEventItem3.setStartDate(startDate.getTime());
        trippyEventItem3.setEndDate(endDate.getTime());
        trippyEventItem3.setPrice(20.0);
        trippyEventItem3.setEventImage("trippyEventItem3.jpg");
        trippyEventItem3.setAddress("80 Mandai Lake Rd, Singapore 729826");
        trippyEventItem3.setEventType(eventTypes);
        trippyEventItem3.setSoftDelete(false);
        trippyEventItem3.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem3);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem500 = new TrippyEventItem();
        trippyEventItem500.setEventName("I.N.U. Cafe");
        trippyEventItem500.setEventDescription("The previously known Paw Pet-radise has been bought over and rebranded to I.N.U (photo above is old). We have yet to visit but from the photos, there seems to be an improvement to the decor. They have menus for both humans and pets.");
        trippyEventItem500.setPoint(0L);
        trippyEventItem500.setPrice(15.0);
        trippyEventItem500.setEventImage("trippyEventItem500.jpg");
        trippyEventItem500.setAddress("530 Balestier Road Monville Mansion #01-07");
        trippyEventItem500.setEventType(eventTypes);
        trippyEventItem500.setSoftDelete(false);
        trippyEventItem500.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem500);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem501 = new TrippyEventItem();
        trippyEventItem501.setEventName("Canine Cafe");
        trippyEventItem501.setEventDescription("Opened by Canine Wellness & Rehab Centre, Canine Cafe is located right at their second hydrotherapy centre. After your dog’s swim session, hang out here and indulge in their pasta and burgers. Do make a reservation before heading down as seating is very limited.");
        trippyEventItem501.setPoint(0L);
        trippyEventItem501.setPrice(15.0);
        trippyEventItem501.setEventImage("trippyEventItem501.jpg");
        trippyEventItem501.setAddress("12 Jalan Gelenggang, Singapore 578192");
        trippyEventItem501.setEventType(eventTypes);
        trippyEventItem501.setSoftDelete(false);
        trippyEventItem501.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem501);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem502 = new TrippyEventItem();
        trippyEventItem502.setEventName("The Cat Cafe");
        trippyEventItem502.setEventDescription("Fancy petting a furry friend while enjoying your coffee? Our humble place is pack with many furry friends. Check them out!");
        trippyEventItem502.setPoint(0L);
        trippyEventItem502.setPrice(15.0);
        trippyEventItem502.setEventImage("trippyEventItem502.jpg");
        trippyEventItem502.setAddress("241B Victoria Street Level 3");
        trippyEventItem502.setEventType(eventTypes);
        trippyEventItem502.setSoftDelete(false);
        trippyEventItem502.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem502);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem503 = new TrippyEventItem();
        trippyEventItem503.setEventName("Aquarium Iwarna");
        trippyEventItem503.setEventDescription("Accessible only by car, Aquarium Iwarna (from $10.50/hour) is pretty off the grid. That said, this also means it’s a lot less crowded - which is ideal for an activity that requires peace of mind and patience.");
        trippyEventItem503.setPoint(0L);
        trippyEventItem503.setPrice(14.0);
        trippyEventItem503.setEventImage("trippyEventItem503.jpg");
        trippyEventItem503.setAddress("70 Pasir Ris Farmway 3, Singapore 518234");
        trippyEventItem503.setEventType(eventTypes);
        trippyEventItem503.setSoftDelete(false);
        trippyEventItem503.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem503);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem504 = new TrippyEventItem();
        trippyEventItem504.setEventName("Neko No Niwa");
        trippyEventItem504.setEventDescription("Neko No Niwa is the pioneer that sparked off a slew of copycat (pun intended) cat cafes. The cosy shophouse space houses a clowder of adorable kitties – all lovingly rescued by owners Sue Lynn and Sam.");
        trippyEventItem504.setPoint(0L);
        trippyEventItem504.setPrice(12.0);
        trippyEventItem504.setEventImage("trippyEventItem504.jpg");
        trippyEventItem504.setAddress("54A Boat Quay (Level 2), Singapore 049843");
        trippyEventItem504.setEventType(eventTypes);
        trippyEventItem504.setSoftDelete(false);
        trippyEventItem504.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem504);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem505 = new TrippyEventItem();
        trippyEventItem505.setEventName("Qian Hu Fishing");
        trippyEventItem505.setEventDescription("Reminisce the good old kampong days.  Remember when families got together and children were out playing and enjoyed netting fishes from the canals and streams?  Bring your kids down to Qian Hu and let them experience the same excitement and joy of catching their own fishes, bringing them home and caring for them.");
        trippyEventItem505.setPoint(0L);
        trippyEventItem505.setPrice(6.0);
        trippyEventItem505.setEventImage("trippyEventItem505.jpg");
        trippyEventItem505.setAddress("No. 71 Jalan Lekar Singapore 698950");
        trippyEventItem505.setEventType(eventTypes);
        trippyEventItem505.setSoftDelete(false);
        trippyEventItem505.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem505);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem506 = new TrippyEventItem();
        trippyEventItem506.setEventName("Ah Hua Fishing");
        trippyEventItem506.setEventDescription("Located in another secluded area of singapore, prawning at Ah Hua Fishing (from $12/hour) is made more enjoyable thanks to its quiet surroundings and fresh air, away from all distractions.");
        trippyEventItem506.setPoint(0L);
        trippyEventItem506.setPrice(20.0);
        trippyEventItem506.setEventImage("trippyEventItem506.jpg");
        trippyEventItem506.setAddress("10 Neo Tiew Lane 2, Singapore 718813");
        trippyEventItem506.setEventType(eventTypes);
        trippyEventItem506.setSoftDelete(false);
        trippyEventItem506.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem506);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem507 = new TrippyEventItem();
        trippyEventItem507.setEventName("Butterfly Park & Insect Kingdom");
        trippyEventItem507.setEventDescription("Surrounded with lush tropical greenery, Butterfly Park & Insect Kingdom showcases the stunning beauties of mother nature. With a great variety of fluttering butterflies and exotic insects, this tamed wilderness is sure to delight you.");
        trippyEventItem507.setPoint(0L);
        trippyEventItem507.setPrice(20.0);
        trippyEventItem507.setEventImage("trippyEventItem507.jpg");
        trippyEventItem507.setAddress("51 Imbiah Rd, Singapore 099702");
        trippyEventItem507.setEventType(eventTypes);
        trippyEventItem507.setSoftDelete(false);
        trippyEventItem507.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem507);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem508 = new TrippyEventItem();
        trippyEventItem508.setEventName("Jurong Bird Park");
        trippyEventItem508.setEventDescription("Asia’s largest bird park, offering a 20.2-hectare hillside haven for close to 3,500 birds across 400 species, of which 20 per cent are threatened. The bird park is famed for its large and immersive walk-in aviaries such as Lory Loft, Jungle Jewels and the recently revamped Waterfall Aviary. Other unique exhibits include Penguin Coast and Pelican Cove. Jurong Bird Park sees approximately 850,000 visitors annually.");
        trippyEventItem508.setPoint(0L);
        trippyEventItem508.setPrice(30.0);
        trippyEventItem508.setEventImage("trippyEventItem508.jpg");
        trippyEventItem508.setAddress("2 Jurong Hill, Singapore 628925");
        trippyEventItem508.setEventType(eventTypes);
        trippyEventItem508.setSoftDelete(false);
        trippyEventItem508.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem508);

        eventTypes.clear();
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem509 = new TrippyEventItem();
        trippyEventItem509.setEventName("Horse City");
        trippyEventItem509.setEventDescription("Horseback riding can accommodate both the benefits and unique positive emotions. Enjoy the company of the animal the opportunity to everyone - from the beginner to the experienced and child rider. Suitable for people of all physical health. Will help reduce stress, improve the physical condition of the body and soul, will give you courage and strength.");
        trippyEventItem509.setPoint(0L);
        trippyEventItem509.setPrice(50.0);
        trippyEventItem509.setEventImage("trippyEventItem509.jpg");
        trippyEventItem509.setAddress("100 Turf Club Road Singapore 287992");
        trippyEventItem509.setEventType(eventTypes);
        trippyEventItem509.setSoftDelete(false);
        trippyEventItem509.setEventTypeString("Animals and Wildlife");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem509);

        // Initialising the events for adventure
        startDate.set(2018, Calendar.SEPTEMBER, 1);
        endDate.set(2018, Calendar.SEPTEMBER, 16);
        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem5 = new TrippyEventItem();
        trippyEventItem5.setEventName("Sentosa Sandsation");
        trippyEventItem5.setEventDescription("\"This very different sort of beach party indulges you in not just\n"
                + "amazing works of sand art, but also in unique photo \n"
                + "opportunities with these elaborate sculptures. Download the \n"
                + "festival’s dedicated app to see these artworks come alive in \n"
                + "augmented reality.\"");
        trippyEventItem5.setPoint(0L);
        trippyEventItem5.setStartDate(startDate.getTime());
        trippyEventItem5.setEndDate(endDate.getTime());
        trippyEventItem5.setPrice(0.0);
        trippyEventItem5.setEventImage("trippyEventItem5.jpg");
        trippyEventItem5.setAddress("Siloso Beach, Sentosa");
        trippyEventItem5.setEventType(eventTypes);
        trippyEventItem5.setSoftDelete(false);
        trippyEventItem5.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem5);

        startDate.set(2018, Calendar.SEPTEMBER, 24);
        endDate.set(2018, Calendar.OCTOBER, 7);
        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem6 = new TrippyEventItem();
        trippyEventItem6.setEventName("The Science of Kindness");
        trippyEventItem6.setEventDescription("Bring your children to dance and sing along to this musical by KidSTOP and the Singapore Kindness Movement, which highlights to young people that being good to others is, in fact, good for one’s soul.");
        trippyEventItem6.setPoint(0L);
        trippyEventItem6.setStartDate(startDate.getTime());
        trippyEventItem6.setEndDate(endDate.getTime());
        trippyEventItem6.setPrice(0.0);
        trippyEventItem6.setEventImage("trippyEventItem6.jpg");
        trippyEventItem6.setAddress("15 Science Centre Rd, Singapore 609081");
        trippyEventItem6.setEventType(eventTypes);
        trippyEventItem6.setSoftDelete(false);
        trippyEventItem6.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem6);

        startDate.set(2018, Calendar.SEPTEMBER, 28);
        endDate.set(2018, Calendar.OCTOBER, 20);
        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem7 = new TrippyEventItem();
        trippyEventItem7.setEventName("Peter and the Starcatcher");
        trippyEventItem7.setEventDescription("Ever wondered how Peter Pan came to be? Follow him and his friends on this epic prequel to the children’s classic, which will warm your heart at one moment, then have you splitting your sides the next.");
        trippyEventItem7.setPoint(0L);
        trippyEventItem7.setStartDate(startDate.getTime());
        trippyEventItem7.setEndDate(endDate.getTime());
        trippyEventItem7.setPrice(0.0);
        trippyEventItem7.setEventImage("trippyEventItem7.jpg");
        trippyEventItem7.setAddress("#03-01 National Library Building, 100 Victoria St, Singapore 188064");
        trippyEventItem7.setEventType(eventTypes);
        trippyEventItem7.setSoftDelete(false);
        trippyEventItem7.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem7);

        startDate.set(2018, Calendar.SEPTEMBER, 28);
        endDate.set(2018, Calendar.OCTOBER, 31);
        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem8 = new TrippyEventItem();
        trippyEventItem8.setEventName("Halloween Horror Nights 8");
        trippyEventItem8.setEventDescription("With a section of the event themed around the immensely popular Netflix series Stranger Things this year, Southeast Asia’s ultimate Halloween experience dares you to come and meet your ghastly hosts, and take selfies with them afterward.");
        trippyEventItem8.setPoint(0L);
        trippyEventItem8.setStartDate(startDate.getTime());
        trippyEventItem8.setEndDate(endDate.getTime());
        trippyEventItem8.setPrice(58.0);
        trippyEventItem8.setEventImage("trippyEventItem8.jpg");
        trippyEventItem8.setAddress("8 Sentosa Gateway, Sentosa Island, 098269");
        trippyEventItem8.setEventType(eventTypes);
        trippyEventItem8.setSoftDelete(false);
        trippyEventItem8.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem8);

        startDate.set(2018, Calendar.SEPTEMBER, 30);
        endDate.set(2018, Calendar.OCTOBER, 31);
        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem9 = new TrippyEventItem();
        trippyEventItem9.setEventName("Spooky Seas");
        trippyEventItem9.setEventDescription("An unforgettable Halloween celebration awaits in one of the largest aquariums in the world. This peculiar underwater experience of Halloween indulges young and old in spectacular shows, interactive games and storytelling sessions.");
        trippyEventItem9.setPoint(0L);
        trippyEventItem9.setStartDate(startDate.getTime());
        trippyEventItem9.setEndDate(endDate.getTime());
        trippyEventItem9.setPrice(0.0);
        trippyEventItem9.setEventImage("trippyEventItem9.jpg");
        trippyEventItem9.setAddress("8 Sentosa Gateway, Sentosa Island, 098269");
        trippyEventItem9.setEventType(eventTypes);
        trippyEventItem9.setSoftDelete(false);
        trippyEventItem9.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem9);

        startDate.set(2017, Calendar.DECEMBER, 15);
        endDate.set(2018, Calendar.DECEMBER, 15);
        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem10 = new TrippyEventItem();
        trippyEventItem10.setEventName("Madame Tussauds' Marvel 4D Experience");
        trippyEventItem10.setEventDescription("Avengers assemble! Get up close with the Avengers battling it out on the streets of Singapore at Madame Tussauds Singapore, the world’s most popular wax attraction launching the new Marvel 4D Experience. The new interactive Marvel experience will feature figures from Marvel’s most loved Avengers, as well as Asia’s first Marvel 4D Cinema.");
        trippyEventItem10.setPoint(0L);
        trippyEventItem10.setStartDate(startDate.getTime());
        trippyEventItem10.setEndDate(endDate.getTime());
        trippyEventItem10.setPrice(0.0);
        trippyEventItem10.setEventImage("trippyEventItem10.jpg");
        trippyEventItem10.setAddress("40 Imbiah Rd, Imbiah Lookout, 099700");
        trippyEventItem10.setEventType(eventTypes);
        trippyEventItem10.setSoftDelete(false);
        trippyEventItem10.setEventTypeString("Adventure");
        trippyEventSessionLocal.createTrippyEvent(trippyEventItem10);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem11 = new TrippyEventItem();
        trippyEventItem11.setEventName("The Southern Ridges");
        trippyEventItem11.setEventDescription("Bridging the hills of Mount Faber Park, Telok Blangah Hill Park and Kent Ridge Park, the Southern Ridges combine invigorating exercise with spectacular views across Singapore.");
        trippyEventItem11.setPoint(0L);
        trippyEventItem11.setStartDate(null);
        trippyEventItem11.setEndDate(null);
        trippyEventItem11.setPrice(0.0);
        trippyEventItem11.setEventImage("trippyEventItem11.jpg");
        trippyEventItem11.setAddress("Alexandra Road | Mount Faber Park | Telok Blangah Hill Park | Kent Ridge Park");
        trippyEventItem11.setEventType(eventTypes);
        trippyEventItem11.setSoftDelete(false);
        trippyEventItem11.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem11);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem12 = new TrippyEventItem();
        trippyEventItem12.setEventName("Bukit Timah Nature Reserve");
        trippyEventItem12.setEventDescription("This protected rainforest, just 12km away from the city's high-rises, offers hiking and bike trails for observing some of the rarest insect, bird and mammal species in the world.");
        trippyEventItem12.setPoint(0L);
        trippyEventItem12.setStartDate(null);
        trippyEventItem12.setEndDate(null);
        trippyEventItem12.setPrice(0.0);
        trippyEventItem12.setEventImage("trippyEventItem12.jpg");
        trippyEventItem12.setAddress("177 Hindhede Drive Bukit Timah Vhf Station, Singapore 589333");
        trippyEventItem12.setEventType(eventTypes);
        trippyEventItem12.setSoftDelete(false);
        trippyEventItem12.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem12);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem13 = new TrippyEventItem();
        trippyEventItem13.setEventName("Faber Peak Singapore");
        trippyEventItem13.setEventDescription("The city’s unique hilltop destination filled with natural serenity, spectacular views, delectable dining, cosy event venues and its own cable car network. Away from the hustle and bustle of the city, Faber Peak Singapore (formerly The Jewel Box) features a vibrant cluster of dining and entertainment, cable car joyrides and unique experiences enjoyed by local residents and international travellers at the peak of Mount Faber.");
        trippyEventItem13.setPoint(0L);
        trippyEventItem13.setStartDate(null);
        trippyEventItem13.setEndDate(null);
        trippyEventItem13.setPrice(0.0);
        trippyEventItem13.setEventImage("trippyEventItem13.jpg");
        trippyEventItem13.setAddress("109 Mount Faber Road Faber Peak, Singapore 099203");
        trippyEventItem13.setEventType(eventTypes);
        trippyEventItem13.setSoftDelete(false);
        trippyEventItem13.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem13);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem14 = new TrippyEventItem();
        trippyEventItem14.setEventName("Night Safari Wallaby Trail");
        trippyEventItem14.setEventDescription("Among Night Safari’s newest attractions, this captivating Wallaby Trail is inspired by the unique wildlife of the Australian Outback and highland forests. Here, you can look forward to some of Australia’s most fascinating nocturnal wildlife natives. A hop and skip away from the main entrance of the park, this magnificent trail includes an immersive Ranger Station learning experience. This trail also highlights one of the few free-ranging Wallaby walk-through habitats in Asia, and the mysterious Naracoorte Cave with scorpions, venomous centipedes and other night crawling creatures.");
        trippyEventItem14.setPoint(0L);
        trippyEventItem14.setStartDate(null);
        trippyEventItem14.setEndDate(null);
        trippyEventItem14.setPrice(69.0);
        trippyEventItem14.setEventImage("trippyEventItem14.jpg");
        trippyEventItem14.setAddress("80 Mandai Lake Road Singapore Zoological Gardens, Singapore 729826");
        trippyEventItem14.setEventType(eventTypes);
        trippyEventItem14.setSoftDelete(false);
        trippyEventItem14.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem14);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem15 = new TrippyEventItem();
        trippyEventItem15.setEventName("Dairy Farm Nature Park");
        trippyEventItem15.setEventDescription("Set in a rustic environment with multiple trails for hiking and biking, the 63-hectare Dairy Farm Nature Park offers a host of recreational activities and amenities such as a nature trail and former quarry. But the park’s main attraction is the Wallace Education Centre, Singapore’s first field study hub for schools.");
        trippyEventItem15.setPoint(0L);
        trippyEventItem15.setStartDate(null);
        trippyEventItem15.setEndDate(null);
        trippyEventItem15.setPrice(0.0);
        trippyEventItem15.setEventImage("trippyEventItem15.jpg");
        trippyEventItem15.setAddress("100 Dairy Farm Road, Singapore 679057");
        trippyEventItem15.setEventType(eventTypes);
        trippyEventItem15.setSoftDelete(false);
        trippyEventItem15.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem15);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem16 = new TrippyEventItem();
        trippyEventItem16.setEventName("Peranakan Trail");
        trippyEventItem16.setEventDescription("Discover Peranakan culture as you stroll past heritage shophouses, quaint stores and eateries in this charming corner of eastern Singapore.");
        trippyEventItem16.setPoint(0L);
        trippyEventItem16.setStartDate(null);
        trippyEventItem16.setEndDate(null);
        trippyEventItem16.setPrice(66.0);
        trippyEventItem16.setEventImage("trippyEventItem16.jpg");
        trippyEventItem16.setAddress("91 Joo Chiat Road, Singapore 427385");
        trippyEventItem16.setEventType(eventTypes);
        trippyEventItem16.setSoftDelete(false);
        trippyEventItem16.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem16);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem17 = new TrippyEventItem();
        trippyEventItem17.setEventName("Punggol Waterway Park");
        trippyEventItem17.setEventDescription("Built around the section of Punggol Waterway and located along Sentul Crescent, Punggol Waterway Park is a great place for park users to enjoy a fun-filled day of leisure activities. The park is segmented into four different themes: Nature Cove, Recreation Zone, Heritage Zone and Green Gallery.");
        trippyEventItem17.setPoint(0L);
        trippyEventItem17.setStartDate(null);
        trippyEventItem17.setEndDate(null);
        trippyEventItem17.setPrice(0.0);
        trippyEventItem17.setEventImage("trippyEventItem17.jpg");
        trippyEventItem17.setAddress("10 Sentul Crescent Road, Singapore 828851");
        trippyEventItem17.setEventType(eventTypes);
        trippyEventItem17.setSoftDelete(false);
        trippyEventItem17.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem17);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem18 = new TrippyEventItem();
        trippyEventItem18.setEventName("Coney Island Park");
        trippyEventItem18.setEventDescription("Besides being rich in history, the 50 ha Coney Island Park houses a wide variety of habitats, including coastal forests, grasslands, mangroves, and casuarina woodlands. It is home to a wide variety of fauna and flora, some of which are critically endangered. Some plants at the park are presumed nationally extinct in the wild.");
        trippyEventItem18.setPoint(0L);
        trippyEventItem18.setStartDate(null);
        trippyEventItem18.setEndDate(null);
        trippyEventItem18.setPrice(0.0);
        trippyEventItem18.setEventImage("trippyEventItem18.jpg");
        trippyEventItem18.setAddress("Punggol Promenade Nature Walk, Singapore 829325");
        trippyEventItem18.setEventType(eventTypes);
        trippyEventItem18.setSoftDelete(false);
        trippyEventItem18.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem18);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem19 = new TrippyEventItem();
        trippyEventItem19.setEventName("Mandai Track 15");
        trippyEventItem19.setEventDescription("The Mandai Track 15 (or popularly known as T15) is a beginner’s paradise. Smooth flowing trails and easy gradients make T15 one of the most accessible trails in Singapore. The trail starts at Mandai Road near the huge signage of the Singapore Zoo, and ends at the Chestnut Trail and carpark.");
        trippyEventItem19.setPoint(0L);
        trippyEventItem19.setStartDate(null);
        trippyEventItem19.setEndDate(null);
        trippyEventItem19.setPrice(0.0);
        trippyEventItem19.setEventImage("trippyEventItem19.jpg");
        trippyEventItem19.setAddress("451 Mandai Road, Singapore 729753");
        trippyEventItem19.setEventType(eventTypes);
        trippyEventItem19.setSoftDelete(false);
        trippyEventItem19.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem19);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem20 = new TrippyEventItem();
        trippyEventItem20.setEventName("AJ Hackett Sentosa");
        trippyEventItem20.setEventDescription("Singapore’s first and only bungy jump is on Siloso Beach, Sentosa Island. The site features the Skybridge, a 40m walkway with see-through sections. On it, is the Sunset Deck, which has the best sunset views in Siloso. Thrill-seekers then have the choice of doing the Giant Swing, or challenge themselves on the 47m Bungy Jump.");
        trippyEventItem20.setPoint(0L);
        trippyEventItem20.setStartDate(null);
        trippyEventItem20.setEndDate(null);
        trippyEventItem20.setPrice(77.0);
        trippyEventItem20.setEventImage("trippyEventItem20.jpg");
        trippyEventItem20.setAddress("30 Siloso Beach Walk, Singapore 099011");
        trippyEventItem20.setEventType(eventTypes);
        trippyEventItem20.setSoftDelete(false);
        trippyEventItem20.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem20);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem21 = new TrippyEventItem();
        trippyEventItem21.setEventName("Forest Adventure");
        trippyEventItem21.setEventDescription("Come and experience 2.5hours of exhilarating fun on top of the forest canopy at Singapore's only tree top adventure course. Forest Adventure is an obstacles course ate height. The Grand Course (minimum height 1.5m) comprises 44 obstacles including 3 giant zip lines. The Junior Course (minumum height 1.4m) has 26 obstacles and 2 zip lines and the Kids Course (minimum height 1.1m) has 16 obtacles and 2 zip lines. Forest Adventure is set up in a beautiful forest on the banks of Bedok Reservoir. The course has unparalleled view of the water front and offers the perfect gateway from the busy city");
        trippyEventItem21.setPoint(0L);
        trippyEventItem21.setStartDate(null);
        trippyEventItem21.setEndDate(null);
        trippyEventItem21.setPrice(50.0);
        trippyEventItem21.setEventImage("trippyEventItem21.jpg");
        trippyEventItem21.setAddress("825 Bedok Reservoir Road | Bedok Reservoir Park, Singapore 479244");
        trippyEventItem21.setEventType(eventTypes);
        trippyEventItem21.setSoftDelete(false);
        trippyEventItem21.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem21);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem22 = new TrippyEventItem();
        trippyEventItem22.setEventName("Splash-N-Surf");
        trippyEventItem22.setEventDescription("Bring on your sense of adventure and enjoy a variety of recreational water activities at the Singapore Sports Hub! Whether you’re young or young-at-heart, have a splashing good time with your family and friends at our thrilling outdoor water play areas!");
        trippyEventItem22.setPoint(0L);
        trippyEventItem22.setStartDate(null);
        trippyEventItem22.setEndDate(null);
        trippyEventItem22.setPrice(0.0);
        trippyEventItem22.setEventImage("trippyEventItem22.jpg");
        trippyEventItem22.setAddress("1 Stadium Walk Kallang Theatre, Singapore 397688");
        trippyEventItem22.setEventType(eventTypes);
        trippyEventItem22.setSoftDelete(false);
        trippyEventItem22.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem22);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem23 = new TrippyEventItem();
        trippyEventItem23.setEventName("O-Ride Singapore Mini Segway Tours");
        trippyEventItem23.setEventDescription("O-Ride Marina Bay Sands Mini Segway Tour starts at Singapore Sports Hub and reach Marina Bay Sands area, then return to Singapore Sports Hub. The duration of the guided tour is about 3 hours. O-Ride tour guides will help travelers to take beautiful scenery photos at no additional cost. Just relax and enjoy the breeze along the journey. O-Ride Singapore tour guides will teach some of Singapore interesting cultural stuffs to the tourists during the sight-seeing tour.");
        trippyEventItem23.setPoint(0L);
        trippyEventItem23.setStartDate(null);
        trippyEventItem23.setEndDate(null);
        trippyEventItem23.setPrice(120.0);
        trippyEventItem23.setEventImage("trippyEventItem23.jpg");
        trippyEventItem23.setAddress("1 Stadium Place | #02-19 Kallang Wave Mall, Singapore 397688");
        trippyEventItem23.setEventType(eventTypes);
        trippyEventItem23.setSoftDelete(false);
        trippyEventItem23.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem23);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem24 = new TrippyEventItem();
        trippyEventItem24.setEventName("Wildfire Expeditions");
        trippyEventItem24.setEventDescription("Spend a morning with us where we climb and abseil in an amazing natural environment right in Singapore! You will be with a professional instructors, so you are in good hands no matter your level of experience. This is an introductory session, and can be used as practice if you have already done a bit of climbing and abseiling.");
        trippyEventItem24.setPoint(0L);
        trippyEventItem24.setStartDate(null);
        trippyEventItem24.setEndDate(null);
        trippyEventItem24.setPrice(85.0);
        trippyEventItem24.setEventImage("trippyEventItem24.jpg");
        trippyEventItem24.setAddress("21 Bukit Batok Crescent #15-75 Wcega Tower, Singapore 658065");
        trippyEventItem24.setEventType(eventTypes);
        trippyEventItem24.setSoftDelete(false);
        trippyEventItem24.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem24);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem25 = new TrippyEventItem();
        trippyEventItem25.setEventName("Kayak Fishing Fever");
        trippyEventItem25.setEventDescription("Experience the most thrilling way to catch a fish. Speed through the water with the leg-powered Hobie fishing kayaks or Native fishing kayaks. Just bring a hat and sunblock, we have everything else covered!");
        trippyEventItem25.setPoint(0L);
        trippyEventItem25.setStartDate(null);
        trippyEventItem25.setEndDate(null);
        trippyEventItem25.setPrice(150.0);
        trippyEventItem25.setEventImage("trippyEventItem25.jpg");
        trippyEventItem25.setAddress("120 Tanjong Beach Walk, Singapore 098942");
        trippyEventItem25.setEventType(eventTypes);
        trippyEventItem25.setSoftDelete(false);
        trippyEventItem25.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem25);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem26 = new TrippyEventItem();
        trippyEventItem26.setEventName("The Stand Up Paddling School");
        trippyEventItem26.setEventDescription("The SUP School was created in 2012 by Isabelle Malique-Park, a certified Stand Up Paddle Instructor and Flat Water 1 Instructor Trainer (Professional Stand Up Paddle Association, USA). The School operates on booking 7 days a week (no fixed opening hours) and offers a full range of Stand Up Paddle activities - SUP rental, SUP beginner and advanced classes, SUP fitness/yoga/pilates, SUP birthday parties and SUP teambuilding.");
        trippyEventItem26.setPoint(0L);
        trippyEventItem26.setStartDate(null);
        trippyEventItem26.setEndDate(null);
        trippyEventItem26.setPrice(60.0);
        trippyEventItem26.setEventImage("trippyEventItem26.jpg");
        trippyEventItem26.setAddress("112 Tanjong Beach Walk, Singapore 098945");
        trippyEventItem26.setEventType(eventTypes);
        trippyEventItem26.setSoftDelete(false);
        trippyEventItem26.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem26);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem27 = new TrippyEventItem();
        trippyEventItem27.setEventName("Snow City Singapore");
        trippyEventItem27.setEventDescription("Retreat from the scorching humidity by going to the 3,000sqm Snow City, Singapore’s first and only indoor snow centre.");
        trippyEventItem27.setPoint(0L);
        trippyEventItem27.setStartDate(null);
        trippyEventItem27.setEndDate(null);
        trippyEventItem27.setPrice(15.0);
        trippyEventItem27.setEventImage("trippyEventItem27.jpg");
        trippyEventItem27.setAddress("21 Jurong Town Hall Road, Singapore 609433");
        trippyEventItem27.setEventType(eventTypes);
        trippyEventItem27.setSoftDelete(false);
        trippyEventItem27.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem27);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem28 = new TrippyEventItem();
        trippyEventItem28.setEventName("Lost SG");
        trippyEventItem28.setEventDescription("LOST SG is a high-tech, top-rated 60 minutes escape room where players have to put together thought-provoking clues, solve seemingly abstract puzzles and race against time to escape from a locked room. Pay attention to every little detail in the rooms, because even the slightest object could be the key to your escape. Using the latest technology, LOST SG brings your advanced gameplay with the next generation immersion and realism.");
        trippyEventItem28.setPoint(0L);
        trippyEventItem28.setStartDate(null);
        trippyEventItem28.setEndDate(null);
        trippyEventItem28.setPrice(21.90);
        trippyEventItem28.setEventImage("trippyEventItem28.jpg");
        trippyEventItem28.setAddress("1 Sophia Road, #03-01/02/03, Singapore 228149");
        trippyEventItem28.setEventType(eventTypes);
        trippyEventItem28.setSoftDelete(false);
        trippyEventItem28.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem28);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem29 = new TrippyEventItem();
        trippyEventItem29.setEventName("Xcape");
        trippyEventItem29.setEventDescription("Xcape has leveled up Singapore Reality Game Scene By Launching Xcape RPG - World's First Interactive Role Playing Investigation Game at its Finest! Against the backdrop of Shanghai in the 40s, players will cosplay as detectives, wealthy night club owner, army marshal, night club singer, reporter and servant. Experience Interactive theater and solve the mystery with double space, double time as compare to our standard escape game");
        trippyEventItem29.setPoint(0L);
        trippyEventItem29.setStartDate(null);
        trippyEventItem29.setEndDate(null);
        trippyEventItem29.setPrice(110.0);
        trippyEventItem29.setEventImage("trippyEventItem29.jpg");
        trippyEventItem29.setAddress("Bugis Village, 160A Rochor Road, 188435");
        trippyEventItem29.setEventType(eventTypes);
        trippyEventItem29.setSoftDelete(false);
        trippyEventItem29.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem29);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem30 = new TrippyEventItem();
        trippyEventItem30.setEventName("Wavehouse");
        trippyEventItem30.setEventDescription("Wave House Sentosa located on the sandy beaches of Sentosa is Asia’s only installation in an archipelago of global Wave Houses that stretches from Durban in South Africa, San Diego in California, Santiago in Chile, and Mallorca in Spain.");
        trippyEventItem30.setPoint(0L);
        trippyEventItem30.setStartDate(null);
        trippyEventItem30.setEndDate(null);
        trippyEventItem30.setPrice(35.0);
        trippyEventItem30.setEventImage("trippyEventItem30.jpg");
        trippyEventItem30.setAddress("36 Siloso Beach Walk, Singapore 099007");
        trippyEventItem30.setEventType(eventTypes);
        trippyEventItem30.setSoftDelete(false);
        trippyEventItem30.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem30);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem31 = new TrippyEventItem();
        trippyEventItem31.setEventName("Palau Ubin Cycling");
        trippyEventItem31.setEventDescription("Pulau Ubin is a little island at the East of Singapore which remains largely undeveloped to this day. There is a small population on the island and its one of the very last places you can see a kampong community. With the always fun one way bum-boat rides to the island being $2.50 and all day bicycle rental going for just $5, you can get an entire day of fun cycling around Pulau Ubin for just $10. Bring lots of sunblock and insect repellent though.");
        trippyEventItem31.setPoint(0L);
        trippyEventItem31.setStartDate(null);
        trippyEventItem31.setEndDate(null);
        trippyEventItem31.setPrice(15.0);
        trippyEventItem31.setEventImage("trippyEventItem31.jpg");
        trippyEventItem31.setAddress("Pulau Ubin");
        trippyEventItem31.setEventType(eventTypes);
        trippyEventItem31.setSoftDelete(false);
        trippyEventItem31.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem31);

        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem32 = new TrippyEventItem();
        trippyEventItem32.setEventName("Universal Studio Singapore");
        trippyEventItem32.setEventDescription("Southeast Asia’s first and only Universal Studios theme park, featuring 24 rides, shows and attractions in seven themed zones.");
        trippyEventItem32.setPoint(0L);
        trippyEventItem32.setStartDate(null);
        trippyEventItem32.setEndDate(null);
        trippyEventItem32.setPrice(53.0);
        trippyEventItem32.setEventImage("trippyEventItem32.jpg");
        trippyEventItem32.setAddress("8 Sentosa Gateway, Singapore 098269");
        trippyEventItem32.setEventType(eventTypes);
        trippyEventItem32.setSoftDelete(false);
        trippyEventItem32.setEventTypeString("Adventure");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem32);

        // Initialising the events for music and night life
        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem33 = new TrippyEventItem();
        trippyEventItem33.setEventName("Zouk");
        trippyEventItem33.setEventDescription("Party with your friends in Singapore number 1 club");
        trippyEventItem33.setPoint(0L);
        trippyEventItem33.setStartDate(null);
        trippyEventItem33.setEndDate(null);
        trippyEventItem33.setPrice(22.0);
        trippyEventItem33.setEventImage("trippyEventItem33.jpg");
        trippyEventItem33.setAddress("3C River Valley Road, The Cannery, Singapore 179022");
        trippyEventItem33.setEventType(eventTypes);
        trippyEventItem33.setSoftDelete(false);
        trippyEventItem33.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem33);

        startDate.set(2018, Calendar.NOVEMBER, 9);
        endDate.set(2018, Calendar.NOVEMBER, 17);
        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1000 = new TrippyEventItem();
        trippyEventItem1000.setEventName("Indie festival Neon Lights");
        trippyEventItem1000.setEventDescription("It’s set to be bigger than its 2016 run with an eclectic mix of art and music featuring international and local alternative stars carved from the indie cosmos.");
        trippyEventItem1000.setPoint(0L);
        trippyEventItem1000.setStartDate(startDate.getTime());
        trippyEventItem1000.setEndDate(endDate.getTime());
        trippyEventItem1000.setPrice(0.0);
        trippyEventItem1000.setEventImage("trippyEventItem1000.jpg");
        trippyEventItem1000.setAddress("River Valley Rd, Singapore 179037");
        trippyEventItem1000.setEventType(eventTypes);
        trippyEventItem1000.setSoftDelete(false);
        trippyEventItem1000.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1000);

        startDate.set(2018, Calendar.NOVEMBER, 9);
        endDate.set(2018, Calendar.NOVEMBER, 17);
        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1001 = new TrippyEventItem();
        trippyEventItem1001.setEventName("LuLu’s Lounge");
        trippyEventItem1001.setEventDescription("Inspired by the back alley clubs of New York in the 60's, LuLu's Lounge is the antithesis of the mega-club experience, with its blinding lights and deafening music.");
        trippyEventItem1001.setPoint(0L);
        trippyEventItem1001.setStartDate(startDate.getTime());
        trippyEventItem1001.setEndDate(endDate.getTime());
        trippyEventItem1001.setPrice(0.0);
        trippyEventItem1001.setEventImage("trippyEventItem1001.jpg");
        trippyEventItem1001.setAddress("Pan Pacific Singapore 7 Raffles Blvd Singapore 039595 ");
        trippyEventItem1001.setEventType(eventTypes);
        trippyEventItem1001.setSoftDelete(false);
        trippyEventItem1001.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1001);

        startDate.set(2018, Calendar.NOVEMBER, 10);
        endDate.set(2019, Calendar.JANUARY, 1);
        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1002 = new TrippyEventItem();
        trippyEventItem1002.setEventName("Christmas on a Great Street");
        trippyEventItem1002.setEventDescription("Witness Orchard Road transform into a shopping belt with over-street light decorations and experiential pop-ups, during the weeks leading up to Christmas. ");
        trippyEventItem1002.setPoint(0L);
        trippyEventItem1002.setStartDate(startDate.getTime());
        trippyEventItem1002.setEndDate(endDate.getTime());
        trippyEventItem1002.setPrice(0.0);
        trippyEventItem1002.setEventImage("trippyEventItem1002.jpg");
        trippyEventItem1002.setAddress("Orchard Road, Singapore 238895");
        trippyEventItem1002.setEventType(eventTypes);
        trippyEventItem1002.setSoftDelete(false);
        trippyEventItem1002.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1002);

        startDate.set(2018, Calendar.NOVEMBER, 14);
        endDate.set(2018, Calendar.DECEMBER, 31);
        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1003 = new TrippyEventItem();
        trippyEventItem1003.setEventName("Guards at the Taj");
        trippyEventItem1003.setEventDescription("This dark comedy’s witty humour and whirlwind of emotions won it the Obie Award for Best New Play in 2016. ");
        trippyEventItem1003.setPoint(0L);
        trippyEventItem1003.setStartDate(startDate.getTime());
        trippyEventItem1003.setEndDate(endDate.getTime());
        trippyEventItem1003.setPrice(45.0);
        trippyEventItem1003.setEventImage("trippyEventItem1003.jpg");
        trippyEventItem1003.setAddress("KC Arts Centre, 20 Merbau Road");
        trippyEventItem1003.setEventType(eventTypes);
        trippyEventItem1003.setSoftDelete(false);
        trippyEventItem1003.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1003);

        startDate.set(2018, Calendar.DECEMBER, 1);
        endDate.set(2018, Calendar.DECEMBER, 1);
        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1004 = new TrippyEventItem();
        trippyEventItem1004.setEventName("2018 ZoukOut");
        trippyEventItem1004.setEventDescription("FOR ONE NIGHT ONLY. 16 HOURS. 4PM – 8AM. 30,000 GUESTS. FROM SUNSET TO SUNRISE. THE WORLD’S BIGGEST NAMES.");
        trippyEventItem1004.setPoint(0L);
        trippyEventItem1004.setStartDate(startDate.getTime());
        trippyEventItem1004.setEndDate(endDate.getTime());
        trippyEventItem1004.setPrice(158.0);
        trippyEventItem1004.setEventImage("trippyEventItem1004.jpg");
        trippyEventItem1004.setAddress("Siloso Beach, SENTOSA");
        trippyEventItem1004.setEventType(eventTypes);
        trippyEventItem1004.setSoftDelete(false);
        trippyEventItem1004.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1004);

        startDate.set(2018, Calendar.DECEMBER, 1);
        endDate.set(2018, Calendar.DECEMBER, 1);
        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1005 = new TrippyEventItem();
        trippyEventItem1005.setEventName("MAMBO JAMBO BEACH PARTY");
        trippyEventItem1005.setEventDescription("This will be an afternoon of nostalgia, where alumni of Zouk pioneers and a new generation of Zouk guest DJs and residents gather for one night only to celebrate 18 years of ZoukOut. ");
        trippyEventItem1005.setPoint(0L);
        trippyEventItem1005.setStartDate(startDate.getTime());
        trippyEventItem1005.setEndDate(endDate.getTime());
        trippyEventItem1005.setPrice(55.0);
        trippyEventItem1005.setEventImage("trippyEventItem1005.jpg");
        trippyEventItem1005.setAddress("AJ Hackett, Siloso Beach, SENTOSA");
        trippyEventItem1005.setEventType(eventTypes);
        trippyEventItem1005.setSoftDelete(false);
        trippyEventItem1005.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1005);

        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1006 = new TrippyEventItem();
        trippyEventItem1006.setEventName("The Comedy Club (Asia)");
        trippyEventItem1006.setEventDescription("Aptly named “Talk Cock Comedy”, these weekly open mic sessions where comedian-wannabes try their hands at making you laugh by talking cock with you take place on Wednesdays at Blu Jaz Café. ");
        trippyEventItem1006.setPoint(0L);
        trippyEventItem1006.setPrice(15.0);
        trippyEventItem1006.setEventImage("trippyEventItem1006.jpg");
        trippyEventItem1006.setAddress("No. 11 Bali Lane, Blu Jaz Café, Historic Kampong Glam, Singapore 189848");
        trippyEventItem1006.setEventType(eventTypes);
        trippyEventItem1006.setSoftDelete(false);
        trippyEventItem1006.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1006);

        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1007 = new TrippyEventItem();
        trippyEventItem1007.setEventName("Function");
        trippyEventItem1007.setEventDescription("Function is a night at Canvas that harks back to a simpler time when people just wanted to dance, smile and have fun on a night out.");
        trippyEventItem1007.setPoint(0L);
        trippyEventItem1007.setPrice(20.0);
        trippyEventItem1007.setEventImage("trippyEventItem1007.jpg");
        trippyEventItem1007.setAddress("20 Upper Circular Road #B1-01/02/03/04/05/06 The Riverwalk Singapore 058416");
        trippyEventItem1007.setEventType(eventTypes);
        trippyEventItem1007.setSoftDelete(false);
        trippyEventItem1007.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1007);

        startDate.set(2018, Calendar.DECEMBER, 12);
        endDate.set(2018, Calendar.DECEMBER, 12);
        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1008 = new TrippyEventItem();
        trippyEventItem1008.setEventName("Bulletproof presents Ghetto with Matthew");
        trippyEventItem1008.setEventDescription("This isn't a night for kids, this is for the straight-up bulletproof because Ghetto and Matthew will blow them minds with every drop as they deliver the Hip Hop, Pop and Electro swag that will leave believers with an Empire sized state of mind.  ");
        trippyEventItem1008.setPoint(0L);
        trippyEventItem1008.setStartDate(startDate.getTime());
        trippyEventItem1008.setEndDate(endDate.getTime());
        trippyEventItem1008.setPrice(33.0);
        trippyEventItem1008.setEventImage("trippyEventItem1008.jpg");
        trippyEventItem1008.setAddress("17 Jiak Kim Street Singapore 169420");
        trippyEventItem1008.setEventType(eventTypes);
        trippyEventItem1008.setSoftDelete(false);
        trippyEventItem1008.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1008);

        startDate.set(2018, Calendar.DECEMBER, 12);
        endDate.set(2018, Calendar.DECEMBER, 12);
        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1009 = new TrippyEventItem();
        trippyEventItem1009.setEventName("Disco to Funk");
        trippyEventItem1009.setEventDescription("Falling through the wuthering heights of the 90s mostly unscathed, Ginette Chittick and Terrence Tan aka jinmart present to you an eclectic collection of indie rock music of their teenhood.");
        trippyEventItem1009.setPoint(0L);
        trippyEventItem1009.setStartDate(startDate.getTime());
        trippyEventItem1009.setEndDate(endDate.getTime());
        trippyEventItem1009.setPrice(0.0);
        trippyEventItem1009.setEventImage("trippyEventItem1009.jpg");
        trippyEventItem1009.setAddress("20 Upper Circular Road #B1-01/02/03/04/05/06 The Riverwalk Singapore 058416");
        trippyEventItem1009.setEventType(eventTypes);
        trippyEventItem1009.setSoftDelete(false);
        trippyEventItem1009.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1009);

        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1010 = new TrippyEventItem();
        trippyEventItem1010.setEventName("TurNup Thursdays - Ladies Night");
        trippyEventItem1010.setEventDescription("TurNup Thursdays deepens the bassline and tweak the trills to complement a beat-heavy night with free-flow vodka mixes for the ladies from 10pm - 1am.");
        trippyEventItem1010.setPoint(0L);
        trippyEventItem1010.setPrice(20.0);
        trippyEventItem1010.setEventImage("trippyEventItem1010.jpg");
        trippyEventItem1010.setAddress("20 Upper Circular Road #B1-01/02/03/04/05/06 The Riverwalk Singapore 058416");
        trippyEventItem1010.setEventType(eventTypes);
        trippyEventItem1010.setSoftDelete(false);
        trippyEventItem1010.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1010);

        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1011 = new TrippyEventItem();
        trippyEventItem1011.setEventName("Crane Dance at Sentosa Resort");
        trippyEventItem1011.setEventDescription("Falling through the wuthering heights of the 90s mostly unscathed, Ginette Chittick and Terrence Tan aka jinmart present to you an eclectic collection of indie rock music of their teenhood.");
        trippyEventItem1011.setPoint(0L);
        trippyEventItem1011.setPrice(0.0);
        trippyEventItem1011.setEventImage("trippyEventItem1011.jpg");
        trippyEventItem1011.setAddress("Sentosa");
        trippyEventItem1011.setEventType(eventTypes);
        trippyEventItem1011.setSoftDelete(false);
        trippyEventItem1011.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1011);

        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1012 = new TrippyEventItem();
        trippyEventItem1012.setEventName("ATTICA NIGHTCLUB");
        trippyEventItem1012.setEventDescription("Variety is the soul of Attica - the hottest club at Clarke Quay. Its two levels with different characteristics attract party-goers in different ways.");
        trippyEventItem1012.setPoint(0L);
        trippyEventItem1012.setPrice(30.0);
        trippyEventItem1012.setEventImage("trippyEventItem1012.jpg");
        trippyEventItem1012.setAddress("3A River Valley Road, #01- 03 Clarke Quay Singapore 179020");
        trippyEventItem1012.setEventType(eventTypes);
        trippyEventItem1012.setSoftDelete(false);
        trippyEventItem1012.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1012);

        startDate.set(2018, Calendar.DECEMBER, 22);
        endDate.set(2019, Calendar.MARCH, 24);
        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1013 = new TrippyEventItem();
        trippyEventItem1013.setEventName("PRUDENTIAL MARINA BAY CARNIVAL");
        trippyEventItem1013.setEventDescription("Are you ready for some fun, carnival-goers? This year, we are bringing in more thrilling rides from Europe! Stay tuned for more information!");
        trippyEventItem1013.setPoint(0L);
        trippyEventItem1013.setStartDate(startDate.getTime());
        trippyEventItem1013.setEndDate(endDate.getTime());
        trippyEventItem1013.setPrice(0.0);
        trippyEventItem1013.setEventImage("trippyEventItem1013.jpg");
        trippyEventItem1013.setAddress("12A Bayfront Ave, Singapore 018970");
        trippyEventItem1013.setEventType(eventTypes);
        trippyEventItem1013.setSoftDelete(false);
        trippyEventItem1013.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1013);

        startDate.set(2018, Calendar.DECEMBER, 07);
        endDate.set(2018, Calendar.DECEMBER, 07);
        eventTypes.clear();
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem1014 = new TrippyEventItem();
        trippyEventItem1014.setEventName("SWG Christmas Party");
        trippyEventItem1014.setEventDescription("The annual SWG Christmas Party is just around the corner! Book your space now and hope to see you on Friday 7 December 2018 from 7.30pm");
        trippyEventItem1014.setPoint(0L);
        trippyEventItem1014.setStartDate(startDate.getTime());
        trippyEventItem1014.setEndDate(endDate.getTime());
        trippyEventItem1014.setPrice(0.0);
        trippyEventItem1014.setEventImage("trippyEventItem1014.jpg");
        trippyEventItem1014.setAddress("Hollandse Club 22 Camden Park Singapore, 299814");
        trippyEventItem1014.setEventType(eventTypes);
        trippyEventItem1014.setSoftDelete(false);
        trippyEventItem1014.setEventTypeString("Music and Night Life");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1014);

        // Initialising the events for art and culture
        startDate.set(2018, Calendar.SEPTEMBER, 6);
        endDate.set(2018, Calendar.DECEMBER, 9);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem35 = new TrippyEventItem();
        trippyEventItem35.setEventName("6th Singapore International Photography Festival");
        trippyEventItem35.setEventDescription("The Singapore International Photography Festival invites one and all to admire the works of a wide variety of photographers. This immense festivalspanning several galleries thursts both renowned international shutterbugs and Singapoearn stalwarts into the spotlight");
        trippyEventItem35.setPoint(0L);
        trippyEventItem35.setStartDate(startDate.getTime());
        trippyEventItem35.setEndDate(endDate.getTime());
        trippyEventItem35.setPrice(15.0);
        trippyEventItem35.setEventImage("trippyEventItem35.jpg");
        trippyEventItem35.setAddress("120A Prinsep St | DECK, Singapore 187937");
        trippyEventItem35.setEventType(eventTypes);
        trippyEventItem35.setSoftDelete(false);
        trippyEventItem35.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem35);

        startDate.set(2018, Calendar.SEPTEMBER, 6);
        endDate.set(2019, Calendar.JANUARY, 27);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem36 = new TrippyEventItem();
        trippyEventItem36.setEventName("A Craftsman's Journey: From Dream to Reality");
        trippyEventItem36.setEventDescription("Join us to experience how Passion is made Possible with our Singaporean Craftsmen! Our craftsmen will be at the Singapore Visitor Centre @ orchardgateway to showcase their talent and share how they pursue their dreams. Take part in the exclusive interactive workshops conducted by our craftsmen, where you will get first-hand experience in making and tasting local produce. The interactive workshop is scheduled every Thursday to Sunday at 10.30am & 11.30am. No registration needed.");
        trippyEventItem36.setPoint(0L);
        trippyEventItem36.setStartDate(startDate.getTime());
        trippyEventItem36.setEndDate(endDate.getTime());
        trippyEventItem36.setPrice(0.0);
        trippyEventItem36.setEventImage("trippyEventItem36.jpg");
        trippyEventItem36.setAddress("216 Orchard Road, orchardgateway@emerald, Singapore 238898");
        trippyEventItem36.setEventType(eventTypes);
        trippyEventItem36.setSoftDelete(false);
        trippyEventItem36.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem36);

        startDate.set(2018, Calendar.NOVEMBER, 13);
        endDate.set(2019, Calendar.NOVEMBER, 14);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem37 = new TrippyEventItem();
        trippyEventItem37.setEventName("COLDPLAY: A HEAD FULL OF DREAMS");
        trippyEventItem37.setEventDescription("One of the world’s biggest bands share their story, in their own words, for the first time. Charting Coldplay’s incredible journey from the backrooms of Camden pubs to stadium-filling superstardom, it is the definitive Coldplay film. ");
        trippyEventItem37.setPoint(0L);
        trippyEventItem37.setStartDate(startDate.getTime());
        trippyEventItem37.setEndDate(endDate.getTime());
        trippyEventItem37.setPrice(13.5);
        trippyEventItem37.setEventImage("trippyEventItem37.jpg");
        trippyEventItem37.setAddress("6001 Beach Road, #05-00, Golden Mile Tower, Singapore 199589");
        trippyEventItem37.setEventType(eventTypes);
        trippyEventItem37.setSoftDelete(false);
        trippyEventItem37.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem37);

        startDate.set(2018, Calendar.AUGUST, 2);
        endDate.set(2019, Calendar.JUNE, 9);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem38 = new TrippyEventItem();
        trippyEventItem38.setEventName("Lim Cheng Hoe: Painting Singapore");
        trippyEventItem38.setEventDescription("Step into the past and see the beautiful evolution of Singapore’s landscape between the 1930s and 1970s at this exhibition by Lim Cheng Hoe. Discover over 60 works, from idyllic rural villages and vibrant boat traffic on the Singapore river, to portraits and still life. As one of Singapore’s pioneer artists and a leading watercolour artist of his time, you definitely don’t want to miss Lim’s exhibition.");
        trippyEventItem38.setPoint(0L);
        trippyEventItem38.setStartDate(startDate.getTime());
        trippyEventItem38.setEndDate(endDate.getTime());
        trippyEventItem38.setPrice(0.0);
        trippyEventItem38.setEventImage("trippyEventItem38.jpg");
        trippyEventItem38.setAddress("1 St Andrew's Rd, Singapore 178957");
        trippyEventItem38.setEventType(eventTypes);
        trippyEventItem38.setSoftDelete(false);
        trippyEventItem38.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem38);

        startDate.set(2018, Calendar.NOVEMBER, 15);
        endDate.set(2018, Calendar.NOVEMBER, 15);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem39 = new TrippyEventItem();
        trippyEventItem39.setEventName("Familiar Favourites: tales of Love");
        trippyEventItem39.setEventDescription("Four Korngold scores painting films of love and honour are the musical inspiration for his violin concerto, to be told this night by 2015 Singapore Violin Competition prize winner Richard Lin.");
        trippyEventItem39.setPoint(0L);
        trippyEventItem39.setStartDate(startDate.getTime());
        trippyEventItem39.setEndDate(endDate.getTime());
        trippyEventItem39.setPrice(15.0);
        trippyEventItem39.setEventImage("trippyEventItem39.jpg");
        trippyEventItem39.setAddress("Esplanade Concert Hall");
        trippyEventItem39.setEventType(eventTypes);
        trippyEventItem39.setSoftDelete(false);
        trippyEventItem39.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem39);

        startDate.set(2018, Calendar.JULY, 29);
        endDate.set(2018, Calendar.DECEMBER, 31);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem40 = new TrippyEventItem();
        trippyEventItem40.setEventName("Quayside's Sunday Floral Market");
        trippyEventItem40.setEventDescription("Admire and purchase beautiful bespoke flower arrangements at this pop-up flower stall. Using a wide range of seasonal blooms including lilies, peonies, tulips and more, these are insta-worthy and sure to make your friends jealous. You can also take part in a DIY potpourri station or floral arrangement workshops to create your own bouquets. Hello Flowers will be at Quayside every last Sunday of the month from 11am to 4pm.Those visiting the floral market can follow #FlowersatQuayside to stay updated.");
        trippyEventItem40.setPoint(0L);
        trippyEventItem40.setStartDate(startDate.getTime());
        trippyEventItem40.setEndDate(endDate.getTime());
        trippyEventItem40.setPrice(0.0);
        trippyEventItem40.setEventImage("trippyEventItem40.jpg");
        trippyEventItem40.setAddress("60 Robertson Quay, Singapore 238252");
        trippyEventItem40.setEventType(eventTypes);
        trippyEventItem40.setSoftDelete(false);
        trippyEventItem40.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem40);

        startDate.set(2018, Calendar.NOVEMBER, 15);
        endDate.set(2018, Calendar.NOVEMBER, 15);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem41 = new TrippyEventItem();
        trippyEventItem41.setEventName("ARTSCIENCE LATE: TAO DANCE THEATRE");
        trippyEventItem41.setEventDescription("In the opening week of Minimalism: Space. Light. Object, Beijing-based TAO Dance Theatre presents Weight x 3, a trio of contemporary dance pieces set to the music of American composer Steve Reich.");
        trippyEventItem41.setPoint(0L);
        trippyEventItem41.setStartDate(startDate.getTime());
        trippyEventItem41.setEndDate(endDate.getTime());
        trippyEventItem41.setPrice(0.0);
        trippyEventItem41.setEventImage("trippyEventItem41.jpg");
        trippyEventItem41.setAddress("ARTSCIENCE MUSEUM Expression Gallery, Level 4");
        trippyEventItem41.setEventType(eventTypes);
        trippyEventItem41.setSoftDelete(false);
        trippyEventItem41.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem41);

        startDate.set(2018, Calendar.DECEMBER, 12);
        endDate.set(2018, Calendar.DECEMBER, 31);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem42 = new TrippyEventItem();
        trippyEventItem42.setEventName("Ancient Religions");
        trippyEventItem42.setEventDescription("The ACM permanent galleries on Level 2 explore how artists have masterfully expressed complex ideas about life and existence with religions in sculpture and paintings. Beginning with the Ancient Religions exhibition, which explores early styles and motifs of Buddhism, Hinduism, and Jainism in India and how they spread to China and the larger Southeast Asia, the story continues in the following galleries as the art developed and evolved through the centuries.");
        trippyEventItem42.setPoint(0L);
        trippyEventItem42.setStartDate(startDate.getTime());
        trippyEventItem42.setEndDate(endDate.getTime());
        trippyEventItem42.setPrice(0.0);
        trippyEventItem42.setEventImage("trippyEventItem42.jpg");
        trippyEventItem42.setAddress("1 Empress Pl, Singapore 179555");
        trippyEventItem42.setEventType(eventTypes);
        trippyEventItem42.setSoftDelete(false);
        trippyEventItem42.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem42);

        startDate.set(2018, Calendar.MAY, 5);
        endDate.set(2019, Calendar.FEBRUARY, 3);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem43 = new TrippyEventItem();
        trippyEventItem43.setEventName("Amek Gambar: Peranakans and Photography");
        trippyEventItem43.setEventDescription("The Peranakan Museum will be presenting its first historical exhibition showcasing one of the world’s largest collection of Peranakan photography. Through more than 200 photographs and portraits, the exhibition traces the history and evolution of photography in the region, with a focus on how the Peranakan community captured and projected themselves to the world through the multi-faceted medium of photographs. Besides catching a glimpse of the personal and social lives of the Peranakans, Amek Gambar: Peranakans and Photography will bring visitors back in time to when photography was first invented and arrived in Singapore.");
        trippyEventItem43.setPoint(0L);
        trippyEventItem43.setStartDate(startDate.getTime());
        trippyEventItem43.setEndDate(endDate.getTime());
        trippyEventItem43.setPrice(0.0);
        trippyEventItem43.setEventImage("trippyEventItem43.jpg");
        trippyEventItem43.setAddress("39 Armenian St, Singapore 179941");
        trippyEventItem43.setEventType(eventTypes);
        trippyEventItem43.setSoftDelete(false);
        trippyEventItem43.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem43);

        startDate.set(2018, Calendar.NOVEMBER, 15);
        endDate.set(2018, Calendar.NOVEMBER, 15);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem44 = new TrippyEventItem();
        trippyEventItem44.setEventName("Sentosa Island Lights");
        trippyEventItem44.setEventDescription("Island Lights dazzles Sentosa’s night scene with interactive day-to-night art installations by local artists, located along Palawan Beach.");
        trippyEventItem44.setPoint(0L);
        trippyEventItem44.setStartDate(startDate.getTime());
        trippyEventItem44.setEndDate(endDate.getTime());
        trippyEventItem44.setPrice(0.0);
        trippyEventItem44.setEventImage("trippyEventItem44.jpg");
        trippyEventItem44.setAddress("Sentosa, Palawan Beach");
        trippyEventItem44.setEventType(eventTypes);
        trippyEventItem44.setSoftDelete(false);
        trippyEventItem44.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem44);

        startDate.set(2018, Calendar.NOVEMBER, 17);
        endDate.set(2018, Calendar.NOVEMBER, 17);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem45 = new TrippyEventItem();
        trippyEventItem45.setEventName("WHAT IS CONTEMPORARY ART");
        trippyEventItem45.setEventDescription("What makes a work of art a work of art? Is contemporary art defined by particular boundaries or characteristics? What does it mean to look at an image in context? How do contexts provide images and objects with meaning? Just as artists and designers create something within a particular context, viewers of art and design enter the work through their own. This talk presents frameworks and examples through which images and objects can be examined analytically and discusses how culture, material, narrative and site contribute to the meanings of work.");
        trippyEventItem45.setPoint(0L);
        trippyEventItem45.setStartDate(startDate.getTime());
        trippyEventItem45.setEndDate(endDate.getTime());
        trippyEventItem45.setPrice(100.0);
        trippyEventItem45.setEventImage("trippyEventItem45.jpg");
        trippyEventItem45.setAddress("1 McNally St, LASELLE Singapore 187940");
        trippyEventItem45.setEventType(eventTypes);
        trippyEventItem45.setSoftDelete(false);
        trippyEventItem45.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem45);

        startDate.set(2018, Calendar.NOVEMBER, 19);
        endDate.set(2018, Calendar.NOVEMBER, 19);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem46 = new TrippyEventItem();
        trippyEventItem46.setEventName("Jordan Rudess - Bach To Rock: A Musician's Journey Tour Singapore");
        trippyEventItem46.setEventDescription("Voted “Best Keyboardist of All Time” (Music Radar Magazine), Rudess’ solo concert will travel through his fascinating musical journey – how a young Juilliard piano prodigy destined for a classical music career evolved into a keyboard rock star phenomenon.");
        trippyEventItem46.setPoint(0L);
        trippyEventItem46.setStartDate(startDate.getTime());
        trippyEventItem46.setEndDate(endDate.getTime());
        trippyEventItem46.setPrice(68.0);
        trippyEventItem46.setEventImage("trippyEventItem46.jpg");
        trippyEventItem46.setAddress("50 Kent Ridge Crescent, National University of Singapore, Singapore 119279");
        trippyEventItem46.setEventType(eventTypes);
        trippyEventItem46.setSoftDelete(false);
        trippyEventItem46.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem46);

        startDate.set(2018, Calendar.NOVEMBER, 20);
        endDate.set(2018, Calendar.NOVEMBER, 20);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem47 = new TrippyEventItem();
        trippyEventItem47.setEventName("In an Instant: Curator Tour");
        trippyEventItem47.setEventDescription("Delve deeper into the story of Polaroid photography with our curator, Priscilla Chua, in an exclusive after-hours tour of the gallery.");
        trippyEventItem47.setPoint(0L);
        trippyEventItem47.setStartDate(startDate.getTime());
        trippyEventItem47.setEndDate(endDate.getTime());
        trippyEventItem47.setPrice(20.0);
        trippyEventItem47.setEventImage("trippyEventItem47.jpg");
        trippyEventItem47.setAddress("93 Stamford Rd, Singapore 178897");
        trippyEventItem47.setEventType(eventTypes);
        trippyEventItem47.setSoftDelete(false);
        trippyEventItem47.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem47);

        startDate.set(2018, Calendar.NOVEMBER, 22);
        endDate.set(2018, Calendar.NOVEMBER, 22);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem48 = new TrippyEventItem();
        trippyEventItem48.setEventName("DVORAK: CELLO CONCERTO");
        trippyEventItem48.setEventDescription(" Ng Pei-Sian stars in the masterly Cello Concerto by Dvořák, music of magisterial demeanour and melodic magnificence. Bulgarian conductor Pavel Baleff returns to conduct this irresistible concert offering that also includes the ever-popular New World Symphony");
        trippyEventItem48.setPoint(0L);
        trippyEventItem48.setStartDate(startDate.getTime());
        trippyEventItem48.setEndDate(endDate.getTime());
        trippyEventItem48.setPrice(15.0);
        trippyEventItem48.setEventImage("trippyEventItem48.jpg");
        trippyEventItem48.setAddress("Esplanade Drive, Esplanade Concert Hall");
        trippyEventItem48.setEventType(eventTypes);
        trippyEventItem48.setSoftDelete(false);
        trippyEventItem48.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem48);

        startDate.set(2018, Calendar.NOVEMBER, 15);
        endDate.set(2019, Calendar.JANUARY, 5);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem49 = new TrippyEventItem();
        trippyEventItem49.setEventName("Marie & The Nutcracker - An immersive theatrical dining experience");
        trippyEventItem49.setEventDescription("This holiday season, join Marie and The Nutcracker as the toys come to life to challenge the Mouse king and his army for the key to the Kingdom of Sweets. Delight in a 4 course interactive dinner inspired by cuisines from Nuremberg and E.T.A. Hoffman's The Nutcracker. Discover the magical world of Marie's imagination, if and only if you look hard enough.");
        trippyEventItem49.setPoint(0L);
        trippyEventItem49.setStartDate(startDate.getTime());
        trippyEventItem49.setEndDate(endDate.getTime());
        trippyEventItem49.setPrice(98.0);
        trippyEventItem49.setEventImage("trippyEventItem49.jpg");
        trippyEventItem49.setAddress("Secret venue to be disclosed");
        trippyEventItem49.setEventType(eventTypes);
        trippyEventItem49.setSoftDelete(false);
        trippyEventItem49.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem49);

        startDate.set(2018, Calendar.NOVEMBER, 15);
        endDate.set(2018, Calendar.NOVEMBER, 17);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem50 = new TrippyEventItem();
        trippyEventItem50.setEventName("liTHE 2018");
        trippyEventItem50.setEventDescription("liTHE 2018 is the annual showcase of T.H.E Second Company featuring 12 dancers; from the highly watchable versatility of the senior artists, to emerging young talent amongst its apprentices and trainee dancers. Three talented Singaporean choreographers, Anthea Seah, Goh Shou Yi and Marcus headline the event from 2017 – 2019 with their original creations. With the success of their premiere in last year's edition, liTHE 2018 is set to be an event you cannot miss this November!");
        trippyEventItem50.setPoint(0L);
        trippyEventItem50.setStartDate(startDate.getTime());
        trippyEventItem50.setEndDate(endDate.getTime());
        trippyEventItem50.setPrice(28.0);
        trippyEventItem50.setEventImage("trippyEventItem50.jpg");
        trippyEventItem50.setAddress("School of the Arts (SOTA), Studio Theatre");
        trippyEventItem50.setEventType(eventTypes);
        trippyEventItem50.setSoftDelete(false);
        trippyEventItem50.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem50);

        startDate.set(2018, Calendar.NOVEMBER, 15);
        endDate.set(2018, Calendar.DECEMBER, 30);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem51 = new TrippyEventItem();
        trippyEventItem51.setEventName("MIND over MATTER");
        trippyEventItem51.setEventDescription("Mazel Galerie Singapore is honoured to present renowned multi-disciplinary French artist Lionel Sabatté's first solo exhibition in Singapore & Asia: MIND over MATTER. It is a showcase of the artist's latest creations in painting, drawing and sculpture through his use and exploration of mysterious and mystical materials.");
        trippyEventItem51.setPoint(0L);
        trippyEventItem51.setStartDate(startDate.getTime());
        trippyEventItem51.setEndDate(endDate.getTime());
        trippyEventItem51.setPrice(0.0);
        trippyEventItem51.setEventImage("trippyEventItem51.jpg");
        trippyEventItem51.setAddress("Mazel Galerie Singapore, Pacific Plaza, 9 Scotts Road #02-14/17, Singapore 228210");
        trippyEventItem51.setEventType(eventTypes);
        trippyEventItem51.setSoftDelete(false);
        trippyEventItem51.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem51);

        startDate.set(2018, Calendar.NOVEMBER, 15);
        endDate.set(2018, Calendar.NOVEMBER, 17);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem52 = new TrippyEventItem();
        trippyEventItem52.setEventName("The Old Woman and the Ox");
        trippyEventItem52.setEventDescription("The Old Woman and the Ox (2017) is a play written by 20-year-old playwright, Isaiah Christopher Lee, which explores the entangled currents of fear, guilt, loss and love.");
        trippyEventItem52.setPoint(0L);
        trippyEventItem52.setStartDate(startDate.getTime());
        trippyEventItem52.setEndDate(endDate.getTime());
        trippyEventItem52.setPrice(35.0);
        trippyEventItem52.setEventImage("trippyEventItem52.jpg");
        trippyEventItem52.setAddress("3615 Jalan Bukit Merah, Singapore 159461");
        trippyEventItem52.setEventType(eventTypes);
        trippyEventItem52.setSoftDelete(false);
        trippyEventItem52.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem52);

        startDate.set(2018, Calendar.NOVEMBER, 16);
        endDate.set(2018, Calendar.NOVEMBER, 18);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem53 = new TrippyEventItem();
        trippyEventItem53.setEventName("Affordable Art Fair Singapore Autumn Edition 201");
        trippyEventItem53.setEventDescription("The Affordable Art Fair, the leading showcase for affordable contemporary art, returns from 16 – 18 November 2018. All set to turn the city pink for its ninth Autumn Edition, the fair will be offering a fresh plethora of local, regional and international talents featuring over 75 galleries.");
        trippyEventItem53.setPoint(0L);
        trippyEventItem53.setStartDate(startDate.getTime());
        trippyEventItem53.setEndDate(endDate.getTime());
        trippyEventItem53.setPrice(20.0);
        trippyEventItem53.setEventImage("trippyEventItem53.jpg");
        trippyEventItem53.setAddress("1 Republic Blvd, Singapore 038975");
        trippyEventItem53.setEventType(eventTypes);
        trippyEventItem53.setSoftDelete(false);
        trippyEventItem53.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem53);

        startDate.set(2018, Calendar.NOVEMBER, 16);
        endDate.set(2018, Calendar.NOVEMBER, 18);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem54 = new TrippyEventItem();
        trippyEventItem54.setEventName("Asian Youth Theatre Festival 2018");
        trippyEventItem54.setEventDescription("The festival concept is driven by the desire to share ideas, methodology and pedagogical approaches with youths around the region, to enlighten, challenge and empower them, with the ultimate aim of progressing the theatrical and artistic landscape within the region.");
        trippyEventItem54.setPoint(0L);
        trippyEventItem54.setStartDate(startDate.getTime());
        trippyEventItem54.setEndDate(endDate.getTime());
        trippyEventItem54.setPrice(0.0);
        trippyEventItem54.setEventImage("trippyEventItem54.jpg");
        trippyEventItem54.setAddress("9 Woodlands Avenue 9, Republic Polytechnic, Singapore 738964");
        trippyEventItem54.setEventType(eventTypes);
        trippyEventItem54.setSoftDelete(false);
        trippyEventItem54.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem54);

        startDate.set(2018, Calendar.NOVEMBER, 16);
        endDate.set(2018, Calendar.NOVEMBER, 25);
        eventTypes.clear();
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem55 = new TrippyEventItem();
        trippyEventItem55.setEventName("Kalaa Utsavam - Indian Festival of Arts");
        trippyEventItem55.setEventDescription("Kalaa Utsavam – Indian Festival of Arts is an annual festival that celebrates Indian arts during the festive period of Deepavali. Launched in 2002 as a three-day festival, Kalaa Utsavam has since grown into a 10-day festival that presents a spectrum of classical to contemporary works by artists from the Indian subcontinent, the Indian diaspora and Singapore.");
        trippyEventItem55.setPoint(0L);
        trippyEventItem55.setStartDate(startDate.getTime());
        trippyEventItem55.setEndDate(endDate.getTime());
        trippyEventItem55.setPrice(20.0);
        trippyEventItem55.setEventImage("trippyEventItem55.jpg");
        trippyEventItem55.setAddress("Esplanade - Theatres on the Bay");
        trippyEventItem55.setEventType(eventTypes);
        trippyEventItem55.setSoftDelete(false);
        trippyEventItem55.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem55);

        startDate.set(2018, Calendar.NOVEMBER, 16);
        endDate.set(2018, Calendar.NOVEMBER, 25);
        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem56 = new TrippyEventItem();
        trippyEventItem56.setEventName("NP Arts Fiesta 2018 Presents The Wizard of Oz");
        trippyEventItem56.setEventDescription("Featuring an all-student cast, ensemble and a live orchestra, this musical is an enchanting revision of the all-time classic by L. Frank Baum, containing beloved songs like Over The Rainbow, We're Off To See The Wizard, and Follow the Yellow Brick Road. Directed by Warren Baumgart Jr. (Wag The Dog Theatre) and starring NP's very own singing sensation Andrea Jane Leopoldo Rojas (Year 3 School of Life Sciences & Chemical Technology) as Dorothy, this 1987 Royal Shakespeare Company adaptation of the musical extravaganza promises a tale of enchantment, wizardry and fun for the whole family. Don't miss your chance to rediscover the story of Oz in this fantastic musical treat at the Esplanade Theatre!");
        trippyEventItem56.setPoint(0L);
        trippyEventItem56.setStartDate(startDate.getTime());
        trippyEventItem56.setEndDate(endDate.getTime());
        trippyEventItem56.setPrice(0.0);
        trippyEventItem56.setEventImage("trippyEventItem56.jpg");
        trippyEventItem56.setAddress("1 Esplanade Dr, Singapore 038981");
        trippyEventItem56.setEventType(eventTypes);
        trippyEventItem56.setSoftDelete(false);
        trippyEventItem56.setEventTypeString("Art and Culture");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem56);

        // Initialising the events for foodie

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem57 = new TrippyEventItem();
        trippyEventItem57.setEventName("Sungei Road Laksa");
        trippyEventItem57.setEventDescription("Among the list of heritage hawker food stalls in Singapore, Sungei Road Laksa is well known among laksa lovers, and it has an interesting story behind it and how they obtained their recipe from a customer who then disappeared. The stall has been serving laksa for decades in the same way – the curry is cooked in an aluminum curry pot over charcoal. At peak times the line at Sungei Road Laksa can stretch across the food court, although luckily the line goes pretty fast as they only serve one dish with no variations.");
        trippyEventItem57.setPoint(0L);
        trippyEventItem57.setPrice(3.0);
        trippyEventItem57.setEventImage("trippyEventItem57.jpg");
        trippyEventItem57.setAddress("#01-27 Jln Berseh, 100, Singapore 200027");
        trippyEventItem57.setEventType(eventTypes);
        trippyEventItem57.setSoftDelete(false);
        trippyEventItem57.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem57);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem58 = new TrippyEventItem();
        trippyEventItem58.setEventName("Outram Park Ya Hua Rou Gu Cha");
        trippyEventItem58.setEventDescription("Ya Hua Bak Kut Teh has its humble beginnings in Outram Park estate, one of the first HDB estates in Singapore.");
        trippyEventItem58.setPoint(0L);
        trippyEventItem58.setPrice(7.0);
        trippyEventItem58.setEventImage("trippyEventItem58.jpg");
        trippyEventItem58.setAddress("7 Keppel Rd, PSA Tanjong Pagar Complex, 089053");
        trippyEventItem58.setEventType(eventTypes);
        trippyEventItem58.setSoftDelete(false);
        trippyEventItem58.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem58);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem59 = new TrippyEventItem();
        trippyEventItem59.setEventName("Nam Sing Hokkien Fried Mee");
        trippyEventItem59.setEventDescription("Nam Sing Fried Hokkien Mee at Old Airport Road FC is one of the legendary hawkers who has been frying this dish for over 50 years!");
        trippyEventItem59.setPoint(0L);
        trippyEventItem59.setPrice(4.0);
        trippyEventItem59.setEventImage("trippyEventItem59.jpg");
        trippyEventItem59.setAddress("51 Old Airport Road, #01-32, Old Airport Road Food Centre, Singapore 390051");
        trippyEventItem59.setEventType(eventTypes);
        trippyEventItem59.setSoftDelete(false);
        trippyEventItem59.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem59);

        startDate.set(2018, Calendar.NOVEMBER, 18);
        endDate.set(2018, Calendar.NOVEMBER, 20);
        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem60 = new TrippyEventItem();
        trippyEventItem60.setEventName("Food Rebel Fridays");
        trippyEventItem60.setEventDescription("Calling All Food Rebels! Your favourite organic and clean living cafe in the CBD just got better. Join us for some Friday rebellion as Kitchen By Food Rebel adds world class organic & bio-dynamic wines, gluten free craft beer and tapas dining to its menu. From November 18th we’ll be extending our opening hours every Friday until 8pm giving wine lovers and clean living fans a chance to enjoy our healthy food just that little bit longer.");
        trippyEventItem60.setPoint(0L);
        trippyEventItem60.setStartDate(startDate.getTime());
        trippyEventItem60.setEndDate(endDate.getTime());
        trippyEventItem60.setPrice(0.0);
        trippyEventItem60.setEventImage("trippyEventItem60.jpg");
        trippyEventItem60.setAddress("28 Stanley Street, Telok Ayer, Singapore 068737");
        trippyEventItem60.setEventType(eventTypes);
        trippyEventItem60.setSoftDelete(false);
        trippyEventItem60.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem60);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem61 = new TrippyEventItem();
        trippyEventItem61.setEventName("Tian Tian Hainanese Chicken Rice");
        trippyEventItem61.setEventDescription("Tian Tian has been voted as the best chicken rice hawker stall in Singapore by local foodies. Anthony Bourdain, an American celebrity chef, host of Travel Channel’s culinary, has reported that “Chicken rice is so fragrant and delicious that it can be eaten on its own”.");
        trippyEventItem61.setPoint(0L);
        trippyEventItem61.setPrice(5.0);
        trippyEventItem61.setEventImage("trippyEventItem61.jpg");
        trippyEventItem61.setAddress("1 Kadayanallur St, 10/11 Maxwell Food Centre, Singapore 069184");
        trippyEventItem61.setEventType(eventTypes);
        trippyEventItem61.setSoftDelete(false);
        trippyEventItem61.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem61);

        startDate.set(2018, Calendar.APRIL, 1);
        endDate.set(2018, Calendar.DECEMBER, 31);
        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem62 = new TrippyEventItem();
        trippyEventItem62.setEventName("New Flavours at Peach Blossoms");
        trippyEventItem62.setEventDescription("Executive Chinese Chef Edward Chong introduces a new à la carte menu at Peach Blossoms. Signature dishes include Flambéed Chinese Wine Spring Chicken, Roasted Tomahawk Steak in \"Xinjiang\" Style accompanied with Green Papaya Salad, Tom Yum Soup with \"Otak\" served in Whole Coconut and Chilled Abalone with Konbu and Jelly Fish served with Yuzu Sauce.");
        trippyEventItem62.setPoint(0L);
        trippyEventItem62.setStartDate(startDate.getTime());
        trippyEventItem62.setEndDate(endDate.getTime());
        trippyEventItem62.setPrice(51.0);
        trippyEventItem62.setEventImage("trippyEventItem62.jpg");
        trippyEventItem62.setAddress("6 Raffles Boulevard Level 5 Marina Square, Singapore 039594");
        trippyEventItem62.setEventType(eventTypes);
        trippyEventItem62.setSoftDelete(false);
        trippyEventItem62.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem62);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem63 = new TrippyEventItem();
        trippyEventItem63.setEventName("Hup Hong Chicken Rice");
        trippyEventItem63.setEventDescription("Located at Yuhua Village Market & Food Centre, this place is well known in the neighborhood for their roasted chicken rice. The chicken had a firm silky texture, and the sauce was nice and garlicky.");
        trippyEventItem63.setPoint(0L);
        trippyEventItem63.setPrice(3.0);
        trippyEventItem63.setEventImage("trippyEventItem63.jpg");
        trippyEventItem63.setAddress("Yuhua Village Market & Food Centre, 254 Jurong East Street 24");
        trippyEventItem63.setEventType(eventTypes);
        trippyEventItem63.setSoftDelete(false);
        trippyEventItem63.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem63);

        startDate.set(2018, Calendar.JULY, 25);
        endDate.set(2018, Calendar.DECEMBER, 31);
        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem64 = new TrippyEventItem();
        trippyEventItem64.setEventName("Heritage High Tea at the Atrium Lounge");
        trippyEventItem64.setEventDescription("Be spoilt for choice and pamper yourself with a delectable array of Asian and Western high tea treats served on a three-tier stand at the Atrium Lounge.");
        trippyEventItem64.setPoint(0L);
        trippyEventItem64.setStartDate(startDate.getTime());
        trippyEventItem64.setEndDate(endDate.getTime());
        trippyEventItem64.setPrice(38.0);
        trippyEventItem64.setEventImage("trippyEventItem64.jpg");
        trippyEventItem64.setAddress("6 Raffles Boulevard Level 5 Marina Square, Singapore 039594");
        trippyEventItem64.setEventType(eventTypes);
        trippyEventItem64.setSoftDelete(false);
        trippyEventItem64.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem64);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem65 = new TrippyEventItem();
        trippyEventItem65.setEventName("HILL STREET CHAR KWAY TEOW");
        trippyEventItem65.setEventDescription("Coming to Singapore’s fried kway teow, Hill Street Char Kway Teow is often the first name to pop up in people’s mind. This food stall made its fame since it was located at Hill Street.");
        trippyEventItem65.setPoint(0L);
        trippyEventItem65.setPrice(4.0);
        trippyEventItem65.setEventImage("trippyEventItem65.jpg");
        trippyEventItem65.setAddress("16 Bedok South Rd, #01-41, Singapore 460016");
        trippyEventItem65.setEventType(eventTypes);
        trippyEventItem65.setSoftDelete(false);
        trippyEventItem65.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem65);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem66 = new TrippyEventItem();
        trippyEventItem66.setEventName("Song Zhou Luo Bo Gao");
        trippyEventItem66.setEventDescription("Song Zhou fried carrot cake was tasteful and the soft texture of the radish made the carrot cake kind of alluring to the taste buds. May not be the best fried carrot cake but 30 over years of experience in frying the fried carrot cake and the queue could be a good endorsement of their standard.");
        trippyEventItem66.setPoint(0L);
        trippyEventItem66.setPrice(4.0);
        trippyEventItem66.setEventImage("trippyEventItem66.jpg");
        trippyEventItem66.setAddress("207 New Upper Changi Rd, Singapore 460207");
        trippyEventItem66.setEventType(eventTypes);
        trippyEventItem66.setSoftDelete(false);
        trippyEventItem66.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem66);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem67 = new TrippyEventItem();
        trippyEventItem67.setEventName("Hill Street Tai Hwa Pork Noodle");
        trippyEventItem67.setEventDescription("Hill Street Tai Hwa Pork Noodle at Crawford Lane has been hailed as Singapore’s best Bak Chor Mee for good reason. Having snagged a Michelin Star last year, they have once again wowed us by retaining the prestigious accolade in the inaugural guide this year.");
        trippyEventItem67.setPoint(0L);
        trippyEventItem67.setPrice(8.0);
        trippyEventItem67.setEventImage("trippyEventItem67.jpg");
        trippyEventItem67.setAddress("Block 466 Crawford Lane #01-12 Singapore 190465");
        trippyEventItem67.setEventType(eventTypes);
        trippyEventItem67.setSoftDelete(false);
        trippyEventItem67.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem67);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem68 = new TrippyEventItem();
        trippyEventItem68.setEventName("Yong Xiang Xing Tou Foo");
        trippyEventItem68.setEventDescription("Located at the fantastic food court at the People’s Park Food Centre in Chinatown, Yong Xiang Xing Tou Foo is one of the most legendary yong tau foo hawker stalls of Singapore. For the entire time the stall is open, there’s a line that snakes around through the tables, everyone waiting for their mid-afternoon tofu snack.");
        trippyEventItem68.setPoint(0L);
        trippyEventItem68.setPrice(4.0);
        trippyEventItem68.setEventImage("trippyEventItem68.jpg");
        trippyEventItem68.setAddress("32 New Market Rd, 01-1084 People’s Park Food Centre, Singapore 050032");
        trippyEventItem68.setEventType(eventTypes);
        trippyEventItem68.setSoftDelete(false);
        trippyEventItem68.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem68);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem69 = new TrippyEventItem();
        trippyEventItem69.setEventName("Daisy's Dream Kitchen");
        trippyEventItem69.setEventDescription("The tradition of enjoying simple yet hearty delicacies, brings everyone closer across the varying cultures, is passed down to the next generation. Here in Daisy’s Dream Kitchen, it exudes the charm of the good old days of Singapore Kampong spirit where it’s an inspiration for their mouth-watering Peranakan dishes and other local delicacies creation.");
        trippyEventItem69.setPoint(0L);
        trippyEventItem69.setPrice(12.5);
        trippyEventItem69.setEventImage("trippyEventItem69.jpg");
        trippyEventItem69.setAddress("Block 517 West Coast Road #01-571 S120517, Singapore 120517");
        trippyEventItem69.setEventType(eventTypes);
        trippyEventItem69.setSoftDelete(false);
        trippyEventItem69.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem69);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem70 = new TrippyEventItem();
        trippyEventItem70.setEventName("Sinar Pagi Nasi Padang");
        trippyEventItem70.setEventDescription("Located at Geylang Serai Food Centre, one of the main Malay and Indonesian hawker food courts in Singapore, is Sinar Pagi Nasi Padang, a well known stall that serves Nasi Padang.");
        trippyEventItem70.setPoint(0L);
        trippyEventItem70.setPrice(4.0);
        trippyEventItem70.setEventImage("trippyEventItem70.jpg");
        trippyEventItem70.setAddress("#02-137, Geylang Serai Market & Food Centre");
        trippyEventItem70.setEventType(eventTypes);
        trippyEventItem70.setSoftDelete(false);
        trippyEventItem70.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem70);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem71 = new TrippyEventItem();
        trippyEventItem71.setEventName("Singapore Zam Zam Murtabak");
        trippyEventItem71.setEventDescription("Having been around since 1908, Singapore Zam Zam is one of the most well known names in Singapore when it comes to murtabak.");
        trippyEventItem71.setPoint(0L);
        trippyEventItem71.setPrice(8.0);
        trippyEventItem71.setEventImage("trippyEventItem71.jpg");
        trippyEventItem71.setAddress("697-699 North Bridge Rd, Singapore 198675");
        trippyEventItem71.setEventType(eventTypes);
        trippyEventItem71.setSoftDelete(false);
        trippyEventItem71.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem71);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem510 = new TrippyEventItem();
        trippyEventItem510.setEventName("Newton Hawker Centre");
        trippyEventItem510.setEventDescription("Newton Food Centre is another popular eating spot that can satisfy your cravings for hawker food. And with close to 100 stalls, it’s no wonder this food centre got chosen to be featured in Crazy Rich Asians.");
        trippyEventItem510.setPoint(0L);
        trippyEventItem510.setPrice(5.0);
        trippyEventItem510.setEventImage("trippyEventItem510.jpg");
        trippyEventItem510.setAddress("500 Clemenceau Avenue North, Newton Food Centre, Singapore 229495");
        trippyEventItem510.setEventType(eventTypes);
        trippyEventItem510.setSoftDelete(false);
        trippyEventItem510.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem510);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem511 = new TrippyEventItem();
        trippyEventItem511.setEventName("Lola's Cafe");
        trippyEventItem511.setEventDescription("Newton Food Centre is another popular eating spot that can satisfy your cravings for hawker food. And with close to 100 stalls, it’s no wonder this food centre got chosen to be featured in Crazy Rich Asians.");
        trippyEventItem511.setPoint(0L);
        trippyEventItem511.setPrice(20.0);
        trippyEventItem511.setEventImage("trippyEventItem511.jpg");
        trippyEventItem511.setAddress("500 Clemenceau Avenue North, Newton Food Centre, Singapore 229495");
        trippyEventItem511.setEventType(eventTypes);
        trippyEventItem511.setSoftDelete(false);
        trippyEventItem511.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem511);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem512 = new TrippyEventItem();
        trippyEventItem512.setEventName("Swee Choon Dim Sum");
        trippyEventItem512.setEventDescription("Swee Choon Tim Sum Restaurant is a well-known and an established local restaurant where people from all walks of life have come to enjoy dim sum for almost 55 years.");
        trippyEventItem512.setPoint(0L);
        trippyEventItem512.setPrice(15.0);
        trippyEventItem512.setEventImage("trippyEventItem512.jpg");
        trippyEventItem512.setAddress("183/185/187/189/191 Jalan Besar Singapore 208882");
        trippyEventItem512.setEventType(eventTypes);
        trippyEventItem512.setSoftDelete(false);
        trippyEventItem512.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem512);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem513 = new TrippyEventItem();
        trippyEventItem513.setEventName("Wen Dao Shi Dim Sum");
        trippyEventItem513.setEventDescription("126 Dim Sum Wen Dao Shi 揾到食 started serving dim sum at 126 Sims Avenue in 1985. The Boon Keng outlet - 1086 Serangoon Road - was opened in 2012.");
        trippyEventItem513.setPoint(0L);
        trippyEventItem513.setPrice(15.0);
        trippyEventItem513.setEventImage("trippyEventItem513.jpg");
        trippyEventItem513.setAddress("126 Sims Avenue Singapore 387449");
        trippyEventItem513.setEventType(eventTypes);
        trippyEventItem513.setSoftDelete(false);
        trippyEventItem513.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem513);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem514 = new TrippyEventItem();
        trippyEventItem514.setEventName("Scissors Cut Curry Rice");
        trippyEventItem514.setEventDescription("Beach Road Scissors Cut Curry Rice 美芝律剪刀剪咖喱饭 was one of the pioneers of Scissor Cut Curry Rice, somewhat like your chye png stall with ingredients cut up for easier consumption, drenched with various gooey sauces.");
        trippyEventItem514.setPoint(0L);
        trippyEventItem514.setPrice(5.0);
        trippyEventItem514.setEventImage("trippyEventItem514.jpg");
        trippyEventItem514.setAddress("229 Jln Besar, Singapore 208905");
        trippyEventItem514.setEventType(eventTypes);
        trippyEventItem514.setSoftDelete(false);
        trippyEventItem514.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem514);

        eventTypes.clear();
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem515 = new TrippyEventItem();
        trippyEventItem515.setEventName("Ng Ah Sio Pork Ribs Soup");
        trippyEventItem515.setEventDescription("Established since the 1950s, Ng Ah Sio Pork Ribs Soup Eating House has long been recognized as one of the best bak kut teh places in Singapore. This famous eating house has a loyal following for its Teochew-style bak kut teh. Besides its signature bak kut teh, Ng Ah Sio also serves a selection of pig-related dishes such as blanched pig liver, blanched pig kidney, pig tail, braised pig intestine, braised pig skin and braised pig trotter.");
        trippyEventItem515.setPoint(0L);
        trippyEventItem515.setPrice(10.0);
        trippyEventItem515.setEventImage("trippyEventItem515.jpg");
        trippyEventItem515.setAddress("208 Rangoon Road, Singapore 218453");
        trippyEventItem515.setEventType(eventTypes);
        trippyEventItem515.setSoftDelete(false);
        trippyEventItem515.setEventTypeString("Foodie");

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem515);

        try {

            //adding saved trips and past trips to c1
            Customer newC1 = customerSessionLocal.getCustomerByEmail("congx2@hotmail.com");

            SavedTrip savedTrip1 = new SavedTrip();
            startDate.set(2018, Calendar.SEPTEMBER, 15); //should be current date. 
            savedTrip1.setSavedDate(startDate.getTime());
            savedTrip1.setPrice(85.0);
            savedTrip1.setEventItem(trippyEventSessionLocal.retrieveEventByEventName("Wildfire Expeditions"));
            savedTrip1.setCustomer(newC1);
            savedTripSessionLocal.createdSavedTrip(savedTrip1);

            newC1.addSavedTrip(savedTripSessionLocal.getNewlyAddSavedTrip());

            SavedTrip savedTrip2 = new SavedTrip();
            startDate.set(2018, Calendar.SEPTEMBER, 15); //should be current date. 
            savedTrip2.setSavedDate(startDate.getTime());
            savedTrip2.setPrice(30.0);
            savedTrip2.setEventItem(trippyEventSessionLocal.retrieveEventByEventName("Jurong Bird Park"));
            savedTrip2.setCustomer(newC1);
            savedTripSessionLocal.createdSavedTrip(savedTrip2);
            newC1.addSavedTrip(savedTripSessionLocal.getNewlyAddSavedTrip());

            SavedTrip savedTrip3 = new SavedTrip();
            startDate.set(2018, Calendar.SEPTEMBER, 15); //should be current date. 
            savedTrip3.setSavedDate(startDate.getTime());
            savedTrip3.setPrice(50.0); //should get from TrippyEvent.getPrice
            savedTrip3.setEventItem(trippyEventSessionLocal.retrieveEventByEventName("Forest Adventure"));
            savedTrip3.setCustomer(newC1);
            savedTripSessionLocal.createdSavedTrip(savedTrip3);
            newC1.addSavedTrip(savedTripSessionLocal.getNewlyAddSavedTrip());

            //init test data for BookedActivity
            
            BookedActivity bt1 = new BookedActivity();
            startDate.set(2018, Calendar.SEPTEMBER, 20); //should be current date. 
            bt1.setBookedDate(startDate.getTime());
            bt1.setBookedBy(newC1);
            bt1.setPrice(20.0);
            bt1.setQty(1);
            bt1.setStatus(true);
            bt1.setIsDone(true);
            bt1.setEventItem(trippyEventSessionLocal.retrieveEventByEventName("Ah Hua Fishing"));
            bookedActivitySessionLocal.createBookedActivity(bt1);
            newC1.addBookedActivity(bookedActivitySessionLocal.getNewlyAddBookedActivity());

            BookedActivity bt2 = new BookedActivity();
            startDate.set(2018, Calendar.SEPTEMBER, 20); //should be current date. 
            bt2.setBookedDate(startDate.getTime());
            bt2.setBookedBy(newC1);
            bt2.setPrice(0.0);
            bt2.setQty(1);
            bt2.setStatus(true);
            bt2.setIsDone(true);
            bt2.setEventItem(trippyEventSessionLocal.retrieveEventByEventName("Food Rebel Fridays"));
            bookedActivitySessionLocal.createBookedActivity(bt2);
            newC1.addBookedActivity(bookedActivitySessionLocal.getNewlyAddBookedActivity());

            BookedActivity bt3 = new BookedActivity();
            startDate.set(2018, Calendar.SEPTEMBER, 29); //should be current date. 
            bt3.setBookedDate(startDate.getTime());
            bt3.setBookedBy(newC1);
            bt3.setPrice(21.9);
            bt3.setQty(1);
            bt3.setStatus(true);
            bt3.setIsDone(false);
            bt3.setEventItem(trippyEventSessionLocal.retrieveEventByEventName("Lost SG"));
            bookedActivitySessionLocal.createBookedActivity(bt3);
            newC1.addBookedActivity(bookedActivitySessionLocal.getNewlyAddBookedActivity());

            BookedActivity bt4 = new BookedActivity();
            startDate.set(2018, Calendar.SEPTEMBER, 29); //should be current date. 
            bt4.setBookedDate(startDate.getTime());
            bt4.setBookedBy(newC1);
            bt4.setPrice(69.0);
            bt4.setQty(1);
            bt4.setStatus(true);
            bt4.setIsDone(true);
            bt4.setEventItem(trippyEventSessionLocal.retrieveEventByEventName("Night Safari Wallaby Trail"));
            bookedActivitySessionLocal.createBookedActivity(bt4);
            newC1.addBookedActivity(bookedActivitySessionLocal.getNewlyAddBookedActivity());

            BookedActivity bt5 = new BookedActivity();
            startDate.set(2018, Calendar.SEPTEMBER, 27); //should be current date. 
            bt5.setBookedDate(startDate.getTime());
            bt5.setBookedBy(newC1);
            bt5.setPrice(15.0);
            bt5.setQty(1);
            bt5.setStatus(false);
            bt5.setIsDone(false);
            bt5.setEventItem(trippyEventSessionLocal.retrieveEventByEventName("Snow City Singapore"));
            bookedActivitySessionLocal.createBookedActivity(bt5);
            newC1.addBookedActivity(bookedActivitySessionLocal.getNewlyAddBookedActivity());

            BookedActivity bt6 = new BookedActivity();
            startDate.set(2018, Calendar.SEPTEMBER, 27); //should be current date. 
            bt6.setBookedDate(startDate.getTime());
            bt6.setBookedBy(newC1);
            bt6.setPrice(77.0);
            bt6.setQty(1);
            bt6.setStatus(true);
            bt6.setIsDone(true);
            bt6.setEventItem(trippyEventSessionLocal.retrieveEventByEventName("AJ Hackett Sentosa"));
            bookedActivitySessionLocal.createBookedActivity(bt5);
            newC1.addBookedActivity(bookedActivitySessionLocal.getNewlyAddBookedActivity());

        } catch (NoResultException ex) {
            Logger.getLogger(DataInitializationSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CustomerAddSavedTripException ex) {
            Logger.getLogger(DataInitializationSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CustomerAddBookedActivityException ex) {
            Logger.getLogger(DataInitializationSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        //init prize
        Prize prize1 = new Prize();
        prize1.setPrizeName("MCDONALD $5 VOUCHER");
        prize1.setPrizeQty(100);
        prize1.setPrizeDescription("Put some yummy in your friends’ tummies! Surprise your loved ones with McDonald’s Gift Certificates. It’s the perfect gift for any occasion.");
        prize1.setPrizePoint(500);
        prize1.setPrizeImage("mcdonald.jpg");
        prize1.setSoftDelete(false);
        prizeSessionLocal.createPrize(prize1);

        Prize prize2 = new Prize();
        prize2.setPrizeName("SHAW $10 VOUCHER");
        prize2.setPrizeQty(100);
        prize2.setPrizeDescription("Enjoy greater savings with Shaw Theatres movie vouchers!");
        prize2.setPrizePoint(1000);
        prize2.setPrizeImage("shaw.jpg");
        prize2.setSoftDelete(false);
        prizeSessionLocal.createPrize(prize2);

        Prize prize3 = new Prize();
        prize3.setPrizeName("SAFRA $10 VOUCHER");
        prize3.setPrizeQty(100);
        prize3.setPrizeDescription("Enjoy greater savings with SAFRA voucher at participating SAFRA club merchants!");
        prize3.setPrizePoint(1000);
        prize3.setPrizeImage("safra.jpg");
        prize3.setSoftDelete(false);
        prizeSessionLocal.createPrize(prize3);

        Prize prize4 = new Prize();
        prize4.setPrizeName("THERMAL FLASK");
        prize4.setPrizeQty(100);
        prize4.setPrizeDescription("ENDO Japan New Double Stainless Steel Vacuumised Bottle / 480ml / CX-5117. Heat Retention up to 6 hours!");
        prize4.setPrizePoint(2000);
        prize4.setPrizeImage("bottle.jpg");
        prize4.setSoftDelete(false);
        prizeSessionLocal.createPrize(prize4);

        Prize prize5 = new Prize();
        prize5.setPrizeName("TRAVEL PILLOW");
        prize5.setPrizeQty(100);
        prize5.setPrizeDescription("OGAWA Plush Touch - Luxurious Travel Pillow with MEMORY FOAM Cushioning");
        prize5.setPrizePoint(2000);
        prize5.setPrizeImage("pillow.jpg");
        prize5.setSoftDelete(false);
        prizeSessionLocal.createPrize(prize5);

        Prize prize6 = new Prize();
        prize6.setPrizeName("OGAWA MASSAGER");
        prize6.setPrizeQty(10);
        prize6.setPrizeDescription("OGAWA Handheld Body Massager. For use at anytime, anywhere. Multi Functional Massager. Convenient Design.");
        prize6.setPrizePoint(10000);
        prize6.setPrizeImage("massage.jpg");
        prize6.setSoftDelete(false);
        prizeSessionLocal.createPrize(prize6);

    }

    private static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
