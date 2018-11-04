/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import entity.TrippyEventItem;
import entity.TrippyEventType;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
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
@Startup
@Singleton
public class DataInitializationSessionBean {

    @PersistenceContext(unitName = "FeelinTrippy-ejbPU")
    private EntityManager em;

    @EJB
    private TrippyEventSessionLocal trippyEventSessionLocal;
    @EJB
    private CustomerSessionLocal customerSessionLocal;
    @EJB
    private TrippyEventTypeSessionLocal trippyEventTypeSessionLocal;
    
    @PostConstruct
    public void postConstruct() {
        if(em.find(Customer.class, 1l) == null){
            initializeData();
        }
    }

    public DataInitializationSessionBean() {
    }

    private void initializeData() {
        //Initialise new user
        Customer c1 = new Customer("user1", encryptPassword("password"), "John", "Tan", true, (byte)2, "91234567", "Testing1@example.com", 0, false);
        Customer c2 = new Customer("user2", encryptPassword("password"), "Peter", "Lee", true, (byte)2, "91234567", "Testing2@example.com", 0, false);
        Customer c3 = new Customer("user3", encryptPassword("password"), "Jane", "Lee", true, (byte)1, "91234567", "Testing3@example.com", 0, false);
        Customer c4 = new Customer("user4", encryptPassword("password"), "Mary", "Sim", true, (byte)1, "91234567", "Testing4@example.com", 0, false);
        Customer c5 = new Customer("user5", encryptPassword("password"), "Victor", "Lim", true, (byte)2, "91234567", "Testing5@example.com", 0, false);
 
        customerSessionLocal.createCustomer(c1);
        customerSessionLocal.createCustomer(c2);
        customerSessionLocal.createCustomer(c3);
        customerSessionLocal.createCustomer(c4);
        customerSessionLocal.createCustomer(c5);
        
        

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
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://4cxqn5j1afk2facwz3mfxg5r-wpengine.netdna-ssl.com/wp-content/uploads/2018/04/All-About-Dogs-Exhibition.jpg");
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem1 = new TrippyEventItem();
        trippyEventItem1.setEventName("All About Dogs Exhibition");
        trippyEventItem1.setEventDescription("Originating from wolf ancestors, this amazing animal is more than a pet. Using its keen sense of smell and hearing, it provides protection, security checks at immigration control, search and rescue, guide and assistance, and herding for farm animals. The All About Dogs exhibition explores the close bond between dogs and humans, earning it the title – man’s best friend, and will also sniff out what the 2018 Earth Dog year has in store for all the 12 animal zodiac signs.");
        trippyEventItem1.setPoint(0L);
        trippyEventItem1.setStartDate(startDate.getTime());
        trippyEventItem1.setEndDate(endDate.getTime());
        trippyEventItem1.setPrice(0.0);
        trippyEventItem1.setEventImage(eventImages);
        trippyEventItem1.setEventType(eventTypes);
        trippyEventItem1.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem1);

        startDate.set(2018, Calendar.AUGUST, 4);
        endDate.set(2018, Calendar.NOVEMBER, 10);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://4cxqn5j1afk2facwz3mfxg5r-wpengine.netdna-ssl.com/wp-content/uploads/2018/06/City-Square-Mall-Quayside-Isle_Paws-for-a-Good-Cause-1.jpg");
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem2 = new TrippyEventItem();
        trippyEventItem2.setEventName("Paws for a Good Cause: Pet Adoption Drive");
        trippyEventItem2.setEventDescription("");
        trippyEventItem2.setPoint(0L);
        trippyEventItem2.setStartDate(startDate.getTime());
        trippyEventItem2.setEndDate(endDate.getTime());
        trippyEventItem2.setPrice(0.0);
        trippyEventItem2.setEventImage(eventImages);
        trippyEventItem2.setEventType(eventTypes);
        trippyEventItem2.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem2);

        startDate.set(2018, Calendar.JULY, 1);
        endDate.set(2018, Calendar.DECEMBER, 31);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://rainforestlumina.wrs.com.sg/images/img-map.jpg");
        eventTypes.add(animals_and_wildlife);

        TrippyEventItem trippyEventItem3 = new TrippyEventItem();
        trippyEventItem3.setEventName("Luminus Zoo");
        trippyEventItem3.setEventDescription("A lush and luminous universe awaits you at Rainforest Lumina, a multimedia night walk on the wild side.");
        trippyEventItem3.setPoint(0L);
        trippyEventItem3.setStartDate(startDate.getTime());
        trippyEventItem3.setEndDate(endDate.getTime());
        trippyEventItem3.setPrice(20.0);
        trippyEventItem3.setEventImage(eventImages);
        trippyEventItem3.setEventType(eventTypes);
        trippyEventItem3.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem3);

        // Initialising the events for adventure
        eventTypes.clear();
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem4 = new TrippyEventItem();
        trippyEventItem4.setEventName("Escape room");
        trippyEventItem4.setEventDescription("escape from rooms through puzzle with friends");
        trippyEventItem4.setPoint(0L);
        trippyEventItem4.setStartDate(null);
        trippyEventItem4.setEndDate(null);
        trippyEventItem4.setPrice(15.0);
        trippyEventItem4.setEventImage(null);
        trippyEventItem4.setEventType(eventTypes);
        trippyEventItem4.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem4);

        startDate.set(2018, Calendar.SEPTEMBER, 1);
        endDate.set(2018, Calendar.SEPTEMBER, 16);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("http://www.visitsingapore.com/editorials/whats-happening-in-singapore/_jcr_content/par/mobile_21_content_sl/sliderccpar1/editorial_generic_co/content/item_3.thumbnail.image-path1.350.197.jpg");
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
        trippyEventItem5.setEventImage(eventImages);
        trippyEventItem5.setEventType(eventTypes);
        trippyEventItem5.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem5);

        startDate.set(2018, Calendar.SEPTEMBER, 24);
        endDate.set(2018, Calendar.OCTOBER, 7);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1295,h_720,f_auto/w_80,x_15,y_15,g_south_west,l_klook_water/activities/707cb66b-%E6%96%B0%E5%8A%A0%E5%9D%A1%E7%A7%91%E5%AD%A6%E9%A6%86-+-%E4%B8%87%E8%B1%A1%E9%A6%86---Klook%E5%AE%A2%E8%B7%AF/ScienceCentreSingapore+OmniTheatreMovie+ButterfliesUp-Close.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem6 = new TrippyEventItem();
        trippyEventItem6.setEventName("The Science of Kindness");
        trippyEventItem6.setEventDescription("Bring your children to dance and sing along to this musical by KidSTOP and the Singapore Kindness Movement, which highlights to young people that being good to others is, in fact, good for one’s soul.");
        trippyEventItem6.setPoint(0L);
        trippyEventItem6.setStartDate(startDate.getTime());
        trippyEventItem6.setEndDate(endDate.getTime());
        trippyEventItem6.setPrice(0.0);
        trippyEventItem6.setEventImage(eventImages);
        trippyEventItem6.setEventType(eventTypes);
        trippyEventItem6.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem6);

        startDate.set(2018, Calendar.SEPTEMBER, 28);
        endDate.set(2018, Calendar.OCTOBER, 20);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("http://www.visitsingapore.com/editorials/whats-happening-in-singapore/_jcr_content/par/mobile_21_content_sl/sliderccpar1/editorial_generic_co/content/item_10.thumbnail.image-path1.350.197.jpg");
        eventImages.add("https://www.sistic.com.sg/sistic/images/events/17488/1532323555494.jpg");
        eventImages.add("https://www.sistic.com.sg/sistic/images/events/17488/1532080903439.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem7 = new TrippyEventItem();
        trippyEventItem7.setEventName("Peter and the Starcatcher");
        trippyEventItem7.setEventDescription("Ever wondered how Peter Pan came to be? Follow him and his friends on this epic prequel to the children’s classic, which will warm your heart at one moment, then have you splitting your sides the next.");
        trippyEventItem7.setPoint(0L);
        trippyEventItem7.setStartDate(startDate.getTime());
        trippyEventItem7.setEndDate(endDate.getTime());
        trippyEventItem7.setPrice(0.0);
        trippyEventItem7.setEventImage(eventImages);
        trippyEventItem7.setEventType(eventTypes);
        trippyEventItem7.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem7);

        startDate.set(2018, Calendar.SEPTEMBER, 28);
        endDate.set(2018, Calendar.OCTOBER, 31);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://dejiki.com/wp-content/uploads/2018/04/2018_HHN8_HeroL.jpg");
        eventImages.add("https://www.rwsentosa.com/reservations/-/media/project/non-gaming/booking-portal/products/events/hh8/halloween-horror-nights-8-admission-ticket.jpg?bc=white&w=287&h=291&as=1&la=en&hash=FA2991E3D300F69EA77A2D5DADA923D0B338FEE8");
        eventImages.add("https://static.tripzilla.com/thumb/e/4/119268_620x.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem8 = new TrippyEventItem();
        trippyEventItem8.setEventName("Halloween Horror Nights 8");
        trippyEventItem8.setEventDescription("With a section of the event themed around the immensely popular Netflix series Stranger Things this year, Southeast Asia’s ultimate Halloween experience dares you to come and meet your ghastly hosts, and take selfies with them afterward.");
        trippyEventItem8.setPoint(0L);
        trippyEventItem8.setStartDate(startDate.getTime());
        trippyEventItem8.setEndDate(endDate.getTime());
        trippyEventItem8.setPrice(58.0);
        trippyEventItem8.setEventImage(eventImages);
        trippyEventItem8.setEventType(eventTypes);
        trippyEventItem8.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem8);

        startDate.set(2018, Calendar.SEPTEMBER, 30);
        endDate.set(2018, Calendar.OCTOBER, 31);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("http://www.visitsingapore.com/editorials/whats-happening-in-singapore/_jcr_content/par/mobile_21_content_sl/sliderccpar1/editorial_generic_co/content/item_13.thumbnail.image-path1.350.197.jpg");
        eventImages.add("http://arwsome.com/wp-content/uploads/2016/09/Spookyseas-2016.jpg");
        eventImages.add("https://bakchormeeboy.files.wordpress.com/2017/10/spookyseas-2017.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem9 = new TrippyEventItem();
        trippyEventItem9.setEventName("Spooky Seas");
        trippyEventItem9.setEventDescription("An unforgettable Halloween celebration awaits in one of the largest aquariums in the world. This peculiar underwater experience of Halloween indulges young and old in spectacular shows, interactive games and storytelling sessions.");
        trippyEventItem9.setPoint(0L);
        trippyEventItem9.setStartDate(startDate.getTime());
        trippyEventItem9.setEndDate(endDate.getTime());
        trippyEventItem9.setPrice(0.0);
        trippyEventItem9.setEventImage(eventImages);
        trippyEventItem9.setEventType(eventTypes);
        trippyEventItem9.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem9);

        startDate.set(2017, Calendar.DECEMBER, 15);
        endDate.set(2018, Calendar.DECEMBER, 15);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://4cxqn5j1afk2facwz3mfxg5r-wpengine.netdna-ssl.com/wp-content/uploads/2017/12/Madame-Tussuads_Marvel_4D_KV1.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem10 = new TrippyEventItem();
        trippyEventItem10.setEventName("Madame Tussauds' Marvel 4D Experience");
        trippyEventItem10.setEventDescription("Avengers assemble! Get up close with the Avengers battling it out on the streets of Singapore at Madame Tussauds Singapore, the world’s most popular wax attraction launching the new Marvel 4D Experience. The new interactive Marvel experience will feature figures from Marvel’s most loved Avengers, as well as Asia’s first Marvel 4D Cinema.");
        trippyEventItem10.setPoint(0L);
        trippyEventItem10.setStartDate(startDate.getTime());
        trippyEventItem10.setEndDate(endDate.getTime());
        trippyEventItem10.setPrice(0.0);
        trippyEventItem10.setEventImage(eventImages);
        trippyEventItem10.setEventType(eventTypes);
        trippyEventItem10.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem10);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://media-cdn.tripadvisor.com/media/photo-s/09/e3/f3/c1/the-southern-ridges.jpg");
        eventImages.add("https://lonelyplanetimages.imgix.net/a/g/hi/t/83d57059dce56a315dbf088c47da68b1-southern-ridges.jpg");
        eventImages.add("https://media-cdn.tripadvisor.com/media/photo-s/0d/fb/a7/9e/southern-ridges-map.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem11 = new TrippyEventItem();
        trippyEventItem11.setEventName("The Southern Ridges");
        trippyEventItem11.setEventDescription("Bridging the hills of Mount Faber Park, Telok Blangah Hill Park and Kent Ridge Park, the Southern Ridges combine invigorating exercise with spectacular views across Singapore.");
        trippyEventItem11.setPoint(0L);
        trippyEventItem11.setStartDate(null);
        trippyEventItem11.setEndDate(null);
        trippyEventItem11.setPrice(0.0);
        trippyEventItem11.setEventImage(eventImages);
        trippyEventItem11.setEventType(eventTypes);
        trippyEventItem11.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem11);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("http://www.bukitpanjang.com/uploads/2/3/7/3/23733606/1261221_orig.jpg");
        eventImages.add("https://sg1-cdn.pgimgs.com/cms/news/2015/04/Bukit-Timah-nature-reserve-visitor-centre.original.jpg");
        eventImages.add("https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/bukit-timah-nature-reserve/btmapnov2016.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem12 = new TrippyEventItem();
        trippyEventItem12.setEventName("Bukit Timah Nature Reserve");
        trippyEventItem12.setEventDescription("This protected rainforest, just 12km away from the city's high-rises, offers hiking and bike trails for observing some of the rarest insect, bird and mammal species in the world.");
        trippyEventItem12.setPoint(0L);
        trippyEventItem12.setStartDate(null);
        trippyEventItem12.setEndDate(null);
        trippyEventItem12.setPrice(0.0);
        trippyEventItem12.setEventImage(eventImages);
        trippyEventItem12.setEventType(eventTypes);
        trippyEventItem12.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem12);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://media-cdn.tripadvisor.com/media/photo-s/11/3b/69/77/faber-peak-singapore.jpg");
        eventImages.add("https://theinfluencermedia.files.wordpress.com/2015/11/mflg_countdown-at-faber-peak-singapore.jpg?w=1120");
        eventImages.add("https://i.hungrygowhere.com/cms/92/d2/45/1c/20013733/MtFaber_566x424_fillbg_0ab50fbffb.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem13 = new TrippyEventItem();
        trippyEventItem13.setEventName("Faber Peak Singapore");
        trippyEventItem13.setEventDescription("The city’s unique hilltop destination filled with natural serenity, spectacular views, delectable dining, cosy event venues and its own cable car network. Away from the hustle and bustle of the city, Faber Peak Singapore (formerly The Jewel Box) features a vibrant cluster of dining and entertainment, cable car joyrides and unique experiences enjoyed by local residents and international travellers at the peak of Mount Faber.");
        trippyEventItem13.setPoint(0L);
        trippyEventItem13.setStartDate(null);
        trippyEventItem13.setEndDate(null);
        trippyEventItem13.setPrice(0.0);
        trippyEventItem13.setEventImage(eventImages);
        trippyEventItem13.setEventType(eventTypes);
        trippyEventItem13.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem13);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://media-cdn.tripadvisor.com/media/photo-s/11/71/14/5f/look-forward-to-some.jpg");
        eventImages.add("https://journey.symphonyoflove.net/wp-content/uploads/2014/11/Wallaby-Trail-Night-Safari-Wildlife-Parks-Singapore.jpg");
        eventImages.add("https://www.evaair.com/images/englobal/%E5%A4%9C%E5%B7%A1%E9%87%8E%E7%94%9F%E5%8B%95%E7%89%A9%E5%9C%922_tcm33-50471.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem14 = new TrippyEventItem();
        trippyEventItem14.setEventName("Night Safari Wallaby Trail");
        trippyEventItem14.setEventDescription("Among Night Safari’s newest attractions, this captivating Wallaby Trail is inspired by the unique wildlife of the Australian Outback and highland forests. Here, you can look forward to some of Australia’s most fascinating nocturnal wildlife natives. A hop and skip away from the main entrance of the park, this magnificent trail includes an immersive Ranger Station learning experience. This trail also highlights one of the few free-ranging Wallaby walk-through habitats in Asia, and the mysterious Naracoorte Cave with scorpions, venomous centipedes and other night crawling creatures.");
        trippyEventItem14.setPoint(0L);
        trippyEventItem14.setStartDate(null);
        trippyEventItem14.setEndDate(null);
        trippyEventItem14.setPrice(69.0);
        trippyEventItem14.setEventImage(eventImages);
        trippyEventItem14.setEventType(eventTypes);
        trippyEventItem14.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem14);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/dairy-farm/dairyfarmthumb.jpg");
        eventImages.add("http://thesmartlocal.com/images/easyblog_articles/5308/b2ap3_thumbnail_singapore-quarry-dairy-farm-nature-reserve.jpg");
        eventImages.add("http://1step1footprint.com/wp-content/uploads/2016/02/Dairy-Farm-Nature-Park.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem15 = new TrippyEventItem();
        trippyEventItem15.setEventName("Dairy Farm Nature Park");
        trippyEventItem15.setEventDescription("Set in a rustic environment with multiple trails for hiking and biking, the 63-hectare Dairy Farm Nature Park offers a host of recreational activities and amenities such as a nature trail and former quarry. But the park’s main attraction is the Wallace Education Centre, Singapore’s first field study hub for schools.");
        trippyEventItem15.setPoint(0L);
        trippyEventItem15.setStartDate(null);
        trippyEventItem15.setEndDate(null);
        trippyEventItem15.setPrice(0.0);
        trippyEventItem15.setEventImage(eventImages);
        trippyEventItem15.setEventType(eventTypes);
        trippyEventItem15.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem15);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://assets.isango.com/productimages/product/2247/singapore_5084_3.jpg");
        eventImages.add("http://mytravease.com/media/catalog/product/cache/1/thumbnail/600x/05e17a266b0e9cc26fb81a2e0bed7e78/p/e/peranakan_trail_2.jpg");
        eventImages.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVsKPjD1BRDz7_YQI61L_L09BsQcagot_cgbXqSqQb6ZlsoqAWEQ");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem16 = new TrippyEventItem();
        trippyEventItem16.setEventName("Peranakan Trail");
        trippyEventItem16.setEventDescription("Discover Peranakan culture as you stroll past heritage shophouses, quaint stores and eateries in this charming corner of eastern Singapore.");
        trippyEventItem16.setPoint(0L);
        trippyEventItem16.setStartDate(null);
        trippyEventItem16.setEndDate(null);
        trippyEventItem16.setPrice(66.0);
        trippyEventItem16.setEventImage(eventImages);
        trippyEventItem16.setEventType(eventTypes);
        trippyEventItem16.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem16);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://storage.googleapis.com/ehimages/2017/12/9/img_8e563a276be0d83fd39daf5a56db306e_1512844510234_original.jpg");
        eventImages.add("https://www.justrunlah.com/wp-content/uploads/2015/02/punggolww-g01e.jpg");
        eventImages.add("http://www.punggol.com/uploads/2/3/7/3/23733606/4722622_orig.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem17 = new TrippyEventItem();
        trippyEventItem17.setEventName("Punggol Waterway Park");
        trippyEventItem17.setEventDescription("Built around the section of Punggol Waterway and located along Sentul Crescent, Punggol Waterway Park is a great place for park users to enjoy a fun-filled day of leisure activities. The park is segmented into four different themes: Nature Cove, Recreation Zone, Heritage Zone and Green Gallery.");
        trippyEventItem17.setPoint(0L);
        trippyEventItem17.setStartDate(null);
        trippyEventItem17.setEndDate(null);
        trippyEventItem17.setPrice(0.0);
        trippyEventItem17.setEventImage(eventImages);
        trippyEventItem17.setEventType(eventTypes);
        trippyEventItem17.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem17);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://media-cdn.tripadvisor.com/media/photo-s/09/50/d9/92/coney-island-park.jpg");
        eventImages.add("http://coneyisland.sg/wp-content/uploads/2015/10/coney-island-park.jpg");
        eventImages.add("http://www.thesmartlocal.com/images/easyblog_articles/2547/b2ap3_medium_Coney-Island-63.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem18 = new TrippyEventItem();
        trippyEventItem18.setEventName("Coney Island Park");
        trippyEventItem18.setEventDescription("Besides being rich in history, the 50 ha Coney Island Park houses a wide variety of habitats, including coastal forests, grasslands, mangroves, and casuarina woodlands. It is home to a wide variety of fauna and flora, some of which are critically endangered. Some plants at the park are presumed nationally extinct in the wild.");
        trippyEventItem18.setPoint(0L);
        trippyEventItem18.setStartDate(null);
        trippyEventItem18.setEndDate(null);
        trippyEventItem18.setPrice(0.0);
        trippyEventItem18.setEventImage(eventImages);
        trippyEventItem18.setEventType(eventTypes);
        trippyEventItem18.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem18);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://www.singaporemtb.com/wp-content/uploads/2015/07/Sworks_Enduro_29-19-1078x516.jpg");
        eventImages.add("https://static.tripzilla.com/thumb/b/7/28855_620x.jpg");
        eventImages.add("https://i.ytimg.com/vi/fM57H32gBuI/maxresdefault.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem19 = new TrippyEventItem();
        trippyEventItem19.setEventName("Mandai Track 15");
        trippyEventItem19.setEventDescription("The Mandai Track 15 (or popularly known as T15) is a beginner’s paradise. Smooth flowing trails and easy gradients make T15 one of the most accessible trails in Singapore. The trail starts at Mandai Road near the huge signage of the Singapore Zoo, and ends at the Chestnut Trail and carpark.");
        trippyEventItem19.setPoint(0L);
        trippyEventItem19.setStartDate(null);
        trippyEventItem19.setEndDate(null);
        trippyEventItem19.setPrice(0.0);
        trippyEventItem19.setEventImage(eventImages);
        trippyEventItem19.setEventType(eventTypes);
        trippyEventItem19.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem19);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://media-cdn.tripadvisor.com/media/photo-s/10/bd/49/c6/swannie.jpg");
        eventImages.add("https://blog.headout.com/wp-content/uploads/2018/06/Feature-Image-AJ-Hackett-e1530194056884.jpg");
        eventImages.add("https://thefunsocial.com/wp-content/uploads/2017/10/Oct3_ajhackett_66.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem20 = new TrippyEventItem();
        trippyEventItem20.setEventName("AJ Hackett Sentosa");
        trippyEventItem20.setEventDescription("Singapore’s first and only bungy jump is on Siloso Beach, Sentosa Island. The site features the Skybridge, a 40m walkway with see-through sections. On it, is the Sunset Deck, which has the best sunset views in Siloso. Thrill-seekers then have the choice of doing the Giant Swing, or challenge themselves on the 47m Bungy Jump.");
        trippyEventItem20.setPoint(0L);
        trippyEventItem20.setStartDate(null);
        trippyEventItem20.setEndDate(null);
        trippyEventItem20.setPrice(77.0);
        trippyEventItem20.setEventImage(eventImages);
        trippyEventItem20.setEventType(eventTypes);
        trippyEventItem20.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem20);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://www.littledayout.com/wp-content/uploads/articles/2017/06-June/20170619-Forest-Adventure/e-forestadventure3.png");
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2010/04/Grand-Course-46.jpg");
        eventImages.add("https://i.ytimg.com/vi/1Wegi9rlAVA/maxresdefault.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem21 = new TrippyEventItem();
        trippyEventItem21.setEventName("Forest Adventure");
        trippyEventItem21.setEventDescription("Come and experience 2.5hours of exhilarating fun on top of the forest canopy at Singapore's only tree top adventure course. Forest Adventure is an obstacles course ate height. The Grand Course (minimum height 1.5m) comprises 44 obstacles including 3 giant zip lines. The Junior Course (minumum height 1.4m) has 26 obstacles and 2 zip lines and the Kids Course (minimum height 1.1m) has 16 obtacles and 2 zip lines. Forest Adventure is set up in a beautiful forest on the banks of Bedok Reservoir. The course has unparalleled view of the water front and offers the perfect gateway from the busy city");
        trippyEventItem21.setPoint(0L);
        trippyEventItem21.setStartDate(null);
        trippyEventItem21.setEndDate(null);
        trippyEventItem21.setPrice(50.0);
        trippyEventItem21.setEventImage(eventImages);
        trippyEventItem21.setEventType(eventTypes);
        trippyEventItem21.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem21);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("http://singaporemotherhood.com/articles/wp-content/uploads/2016/04/IMG_0288.jpg?x17934");
        eventImages.add("http://singaporemotherhood.com/articles/wp-content/uploads/2016/04/IMG_0007.jpg?x17934");
        eventImages.add("https://ajugglingmom.com/wp-content/uploads/2015/07/Splash-N-Surf-012.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem22 = new TrippyEventItem();
        trippyEventItem22.setEventName("Splash-N-Surf");
        trippyEventItem22.setEventDescription("Bring on your sense of adventure and enjoy a variety of recreational water activities at the Singapore Sports Hub! Whether you’re young or young-at-heart, have a splashing good time with your family and friends at our thrilling outdoor water play areas!");
        trippyEventItem22.setPoint(0L);
        trippyEventItem22.setStartDate(null);
        trippyEventItem22.setEndDate(null);
        trippyEventItem22.setPrice(0.0);
        trippyEventItem22.setEventImage(eventImages);
        trippyEventItem22.setEventType(eventTypes);
        trippyEventItem22.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem22);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://media-cdn.tripadvisor.com/media/photo-s/10/d0/5a/a7/now-you-can-tell-people.jpg");
        eventImages.add("https://static.wixstatic.com/media/ee881f_017b15349ce64da0832470bf8314a4ca~mv2.jpg");
        eventImages.add("https://segway-tours-worldwide.com/wp-content/uploads/7-e1516320163698.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem23 = new TrippyEventItem();
        trippyEventItem23.setEventName("O-Ride Singapore Mini Segway Tours");
        trippyEventItem22.setEventDescription("O-Ride Marina Bay Sands Mini Segway Tour starts at Singapore Sports Hub and reach Marina Bay Sands area, then return to Singapore Sports Hub. The duration of the guided tour is about 3 hours. O-Ride tour guides will help travelers to take beautiful scenery photos at no additional cost. Just relax and enjoy the breeze along the journey. O-Ride Singapore tour guides will teach some of Singapore interesting cultural stuffs to the tourists during the sight-seeing tour.");
        trippyEventItem23.setPoint(0L);
        trippyEventItem23.setStartDate(null);
        trippyEventItem23.setEndDate(null);
        trippyEventItem23.setPrice(120.0);
        trippyEventItem23.setEventImage(eventImages);
        trippyEventItem23.setEventType(eventTypes);
        trippyEventItem23.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem23);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://www.threeplaygrounds.com/public/siteevent_event/64/9f/09/98cce_3464.JPG");
        eventImages.add("https://www.threeplaygrounds.com/public/siteevent_event/88/9f/09/98cf2_98d4.JPG");
        eventImages.add("http://3.bp.blogspot.com/-idWfJj3h9D0/TZx3fG9AD2I/AAAAAAAADic/8C0UbmHpG0k/s1600/BSE%2BMarch%2B11%2B%252877%2529.JPG");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem24 = new TrippyEventItem();
        trippyEventItem24.setEventName("Wildfire Expeditions");
        trippyEventItem24.setEventDescription("Spend a morning with us where we climb and abseil in an amazing natural environment right in Singapore! You will be with a professional instructors, so you are in good hands no matter your level of experience. This is an introductory session, and can be used as practice if you have already done a bit of climbing and abseiling.");
        trippyEventItem24.setPoint(0L);
        trippyEventItem24.setStartDate(null);
        trippyEventItem24.setEndDate(null);
        trippyEventItem24.setPrice(85.0);
        trippyEventItem24.setEventImage(eventImages);
        trippyEventItem24.setEventType(eventTypes);
        trippyEventItem24.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem24);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://media-cdn.tripadvisor.com/media/photo-s/13/ed/8e/62/kayak-fishing-with-an.jpg");
        eventImages.add("https://cmxpv89733.i.lithium.com/t5/image/serverpage/image-id/281961i721C2565C7338EA8?v=1.0");
        eventImages.add("http://img.photobucket.com/albums/v341/idpearl/IMG_0540a.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem25 = new TrippyEventItem();
        trippyEventItem25.setEventName("Kayak Fishing Fever");
        trippyEventItem25.setEventDescription("Experience the most thrilling way to catch a fish. Speed through the water with the leg-powered Hobie fishing kayaks or Native fishing kayaks. Just bring a hat and sunblock, we have everything else covered!");
        trippyEventItem25.setPoint(0L);
        trippyEventItem25.setStartDate(null);
        trippyEventItem25.setEndDate(null);
        trippyEventItem25.setPrice(150.0);
        trippyEventItem25.setEventImage(eventImages);
        trippyEventItem25.setEventType(eventTypes);
        trippyEventItem25.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem25);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("http://www.supschool.com.sg/wp-content/uploads/2016/03/celine.jpg");
        eventImages.add("http://i.imgur.com/N1ll38w.jpg");
        eventImages.add("http://www.supschool.com.sg/wp-content/uploads/2012/02/websiteJuly2012-153.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem26 = new TrippyEventItem();
        trippyEventItem26.setEventName("The Stand Up Paddling School");
        trippyEventItem26.setEventDescription("The SUP School was created in 2012 by Isabelle Malique-Park, a certified Stand Up Paddle Instructor and Flat Water 1 Instructor Trainer (Professional Stand Up Paddle Association, USA). The School operates on booking 7 days a week (no fixed opening hours) and offers a full range of Stand Up Paddle activities - SUP rental, SUP beginner and advanced classes, SUP fitness/yoga/pilates, SUP birthday parties and SUP teambuilding.");
        trippyEventItem26.setPoint(0L);
        trippyEventItem26.setStartDate(null);
        trippyEventItem26.setEndDate(null);
        trippyEventItem26.setPrice(60.0);
        trippyEventItem26.setEventImage(eventImages);
        trippyEventItem26.setEventType(eventTypes);
        trippyEventItem26.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem26);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1200,h_630,f_auto/w_80,x_15,y_15,g_south_west,l_klook_water/activities/1a10eec0-Snow-Play-Session-in-Snow-City-Singapore/SnowCitySingapore.jpg");
        eventImages.add("https://www.overseasattractions.com/wp-content/uploads/2013/10/snow-city-singapore-4.jpg");
        eventImages.add("https://www.overseasattractions.com/wp-content/uploads/2013/10/snow-city-singapore-1.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem27 = new TrippyEventItem();
        trippyEventItem27.setEventName("Snow City Singapore");
        trippyEventItem27.setEventDescription("Retreat from the scorching humidity by going to the 3,000sqm Snow City, Singapore’s first and only indoor snow centre.");
        trippyEventItem27.setPoint(0L);
        trippyEventItem27.setStartDate(null);
        trippyEventItem27.setEndDate(null);
        trippyEventItem27.setPrice(15.0);
        trippyEventItem27.setEventImage(eventImages);
        trippyEventItem27.setEventType(eventTypes);
        trippyEventItem27.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem27);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://media-cdn.tripadvisor.com/media/photo-s/07/66/2a/9d/lost-sg.jpg");
        eventImages.add("https://media-cdn.tripadvisor.com/media/photo-s/07/83/13/f8/alcatraz-successful.jpg");
        eventImages.add("https://farm8.staticflickr.com/7616/16531663944_e79167e3a4_c.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem28 = new TrippyEventItem();
        trippyEventItem28.setEventName("Lost SG");
        trippyEventItem28.setEventDescription("LOST SG is a high-tech, top-rated 60 minutes escape room where players have to put together thought-provoking clues, solve seemingly abstract puzzles and race against time to escape from a locked room. Pay attention to every little detail in the rooms, because even the slightest object could be the key to your escape. Using the latest technology, LOST SG brings your advanced gameplay with the next generation immersion and realism.");
        trippyEventItem28.setPoint(0L);
        trippyEventItem28.setStartDate(null);
        trippyEventItem28.setEndDate(null);
        trippyEventItem28.setPrice(21.90);
        trippyEventItem28.setEventImage(eventImages);
        trippyEventItem28.setEventType(eventTypes);
        trippyEventItem28.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem28);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://media-cdn.tripadvisor.com/media/photo-s/08/20/c8/46/xcape-singapore.jpg");
        eventImages.add("http://www.hypeandstuff.com/wp-content/uploads/2017/04/Xcape-Online-e1491799107532.jpg");
        eventImages.add("https://i0.wp.com/jakartahariini.com/wp-content/uploads/2015/08/Xcape-Indonesia-di-PIK-permainan.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem29 = new TrippyEventItem();
        trippyEventItem29.setEventName("Xcape");
        trippyEventItem29.setEventDescription("Xcape has leveled up Singapore Reality Game Scene By Launching Xcape RPG - World's First Interactive Role Playing Investigation Game at its Finest! Against the backdrop of Shanghai in the 40s, players will cosplay as detectives, wealthy night club owner, army marshal, night club singer, reporter and servant. Experience Interactive theater and solve the mystery with double space, double time as compare to our standard escape game");
        trippyEventItem29.setPoint(0L);
        trippyEventItem29.setStartDate(null);
        trippyEventItem29.setEndDate(null);
        trippyEventItem29.setPrice(110.0);
        trippyEventItem29.setEventImage(eventImages);
        trippyEventItem29.setEventType(eventTypes);
        trippyEventItem29.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem29);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("http://static.asiawebdirect.com/m/phuket/portals/www-singapore-com/homepage/activities/wave-house-sentosa/allParagraphs/BucketComponent/ListingContainer/0/BucketList/0/image1/Wave-House-Sentosa-2.jpg");
        eventImages.add("http://stayacity.com/wp-content/uploads/2016/06/img_6450.jpg");
        eventImages.add("https://www.notchbad.com/wp-content/uploads/2016/12/wave-house-sentosa-flowrider-1030x687.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem30 = new TrippyEventItem();
        trippyEventItem30.setEventName("Wavehouse");
        trippyEventItem30.setEventDescription("Wave House Sentosa located on the sandy beaches of Sentosa is Asia’s only installation in an archipelago of global Wave Houses that stretches from Durban in South Africa, San Diego in California, Santiago in Chile, and Mallorca in Spain.");
        trippyEventItem30.setPoint(0L);
        trippyEventItem30.setStartDate(null);
        trippyEventItem30.setEndDate(null);
        trippyEventItem30.setPrice(35.0);
        trippyEventItem30.setEventImage(eventImages);
        trippyEventItem30.setEventType(eventTypes);
        trippyEventItem30.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem30);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://www.alexisjetsets.com/wp-content/uploads/2017/10/pulau-ubin-puaka-hill-alexisjetsets-10-e1507789615399.jpg");
        eventImages.add("https://www.nparks.gov.sg/-/media/ubin/village/quarries-in-pulau-ubin.jpg");
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2017/06/Blaze-the-Trail_Mountain-Biking_2-1.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem31 = new TrippyEventItem();
        trippyEventItem31.setEventName("Palau Ubin Cycling");
        trippyEventItem31.setEventDescription("Pulau Ubin is a little island at the East of Singapore which remains largely undeveloped to this day. There is a small population on the island and its one of the very last places you can see a kampong community. With the always fun one way bum-boat rides to the island being $2.50 and all day bicycle rental going for just $5, you can get an entire day of fun cycling around Pulau Ubin for just $10. Bring lots of sunblock and insect repellent though.");
        trippyEventItem31.setPoint(0L);
        trippyEventItem31.setStartDate(null);
        trippyEventItem31.setEndDate(null);
        trippyEventItem31.setPrice(15.0);
        trippyEventItem31.setEventImage(eventImages);
        trippyEventItem31.setEventType(eventTypes);
        trippyEventItem31.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem31);

        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://www.mysuitcasejourneys.com/wp-content/uploads/2016/07/Singapore-Universal-Studios-01.jpg");
        eventImages.add("https://www.rwsentosa.com/-/media/project/non-gaming/rwsentosa/attractions/universal-studios-singapore/rides/uss-far-far-away-enchanted-airways-v2-1366x666.jpg");
        eventImages.add("http://static.asiawebdirect.com/m/phuket/portals/www-singapore-com/homepage/attractions/universal-studios-singapore/allParagraphs/BucketComponent/ListingContainer/01/image/Universal-Studios-Singapore-2.jpg");
        eventTypes.add(adventure);

        TrippyEventItem trippyEventItem32 = new TrippyEventItem();
        trippyEventItem32.setEventName("Universal Studio Singapore");
        trippyEventItem32.setEventDescription("Southeast Asia’s first and only Universal Studios theme park, featuring 24 rides, shows and attractions in seven themed zones.");
        trippyEventItem32.setPoint(0L);
        trippyEventItem32.setStartDate(null);
        trippyEventItem32.setEndDate(null);
        trippyEventItem32.setPrice(53.0);
        trippyEventItem32.setEventImage(eventImages);
        trippyEventItem32.setEventType(eventTypes);
        trippyEventItem32.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem32);

        // Initialising the events for music and night life
        // Initialising the events for art and culture
        // Initialising the events for foodie
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
