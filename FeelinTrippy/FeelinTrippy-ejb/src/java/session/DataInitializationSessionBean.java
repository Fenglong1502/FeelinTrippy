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
        if (em.find(Customer.class, 1l) == null) {
            initializeData();
        }
    }

    public DataInitializationSessionBean() {
    }

    private void initializeData() {
        //Initialise new user
        Customer c1 = new Customer("user1", encryptPassword("password"), "John", "Tan", true, (byte) 2, "91234567", "Testing1@example.com", 0, false);
        Customer c2 = new Customer("user2", encryptPassword("password"), "Peter", "Lee", true, (byte) 2, "91234567", "Testing2@example.com", 0, false);
        Customer c3 = new Customer("user3", encryptPassword("password"), "Jane", "Lee", true, (byte) 1, "91234567", "Testing3@example.com", 0, false);
        Customer c4 = new Customer("user4", encryptPassword("password"), "Mary", "Sim", true, (byte) 1, "91234567", "Testing4@example.com", 0, false);
        Customer c5 = new Customer("user5", encryptPassword("password"), "Victor", "Lim", true, (byte) 2, "91234567", "Testing5@example.com", 0, false);

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
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://zoukclub.com/wp-content/uploads/2016/11/main.jpg");
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem33 = new TrippyEventItem();
        trippyEventItem33.setEventName("Zouk");
        trippyEventItem33.setEventDescription("Party with your friends in Singapore number 1 club");
        trippyEventItem33.setPoint(0L);
        trippyEventItem33.setStartDate(null);
        trippyEventItem33.setEndDate(null);
        trippyEventItem33.setPrice(22.0);
        trippyEventItem33.setEventImage(eventImages);
        trippyEventItem33.setEventType(eventTypes);
        trippyEventItem33.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem33);

        startDate.set(2018, Calendar.AUGUST, 3);
        endDate.set(2018, Calendar.OCTOBER, 19);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://4cxqn5j1afk2facwz3mfxg5r-wpengine.netdna-ssl.com/wp-content/uploads/2018/07/TGIF-Music-Station-Image-credit-to-SCCC-e1532666912190.jpg");
        eventTypes.add(music_and_night_life);

        TrippyEventItem trippyEventItem34 = new TrippyEventItem();
        trippyEventItem34.setEventName("TGIF Music Station");
        trippyEventItem34.setEventDescription("Presented by the Singapore Chinese Cultural Centre, the TGIF Music Station gathers an exciting line-up of homegrown artistes and rising talents from our local music scene performing live to start up your weekend revelries.\nThe series of music performances will be held on every 1st and 3rd Friday of the month, with free admission for all.\n Special lunch time pop-up performances will also be happening at Tanjong Pagar Centre.\nRefer to the website for exact details of each performance.");
        trippyEventItem34.setPoint(0L);
        trippyEventItem34.setStartDate(startDate.getTime());
        trippyEventItem34.setEndDate(endDate.getTime());
        trippyEventItem34.setPrice(0.0);
        trippyEventItem34.setEventImage(eventImages);
        trippyEventItem34.setEventType(eventTypes);
        trippyEventItem34.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem34);

        // Initialising the events for art and culture
        startDate.set(2018, Calendar.SEPTEMBER, 6);
        endDate.set(2018, Calendar.DECEMBER, 9);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("http://sipf.sg/wp-content/uploads/2018/07/SIPFWebBanner-01-1024x575.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem35 = new TrippyEventItem();
        trippyEventItem35.setEventName("6th Singapore International Photography Festival");
        trippyEventItem35.setEventDescription("The Singapore International Photography Festival invites one and all to admire the works of a wide variety of photographers. This immense festivalspanning several galleries thursts both renowned international shutterbugs and Singapoearn stalwarts into the spotlight");
        trippyEventItem35.setPoint(0L);
        trippyEventItem35.setStartDate(startDate.getTime());
        trippyEventItem35.setEndDate(endDate.getTime());
        trippyEventItem35.setPrice(15.0);
        trippyEventItem35.setEventImage(eventImages);
        trippyEventItem35.setEventType(eventTypes);
        trippyEventItem35.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem35);

        startDate.set(2018, Calendar.SEPTEMBER, 6);
        endDate.set(2019, Calendar.JANUARY, 27);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("http://www.visitsingapore.com/editorials/whats-happening-in-singapore/_jcr_content/par/mobile_21_content_sl/sliderccpar1/editorial_generic_co/content/item_15.thumbnail.image-path1.350.197.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem36 = new TrippyEventItem();
        trippyEventItem36.setEventName("A Craftsman's Journey: From Dream to Reality");
        trippyEventItem36.setEventDescription("Join us to experience how Passion is made Possible with our Singaporean Craftsmen! Our craftsmen will be at the Singapore Visitor Centre @ orchardgateway to showcase their talent and share how they pursue their dreams. Take part in the exclusive interactive workshops conducted by our craftsmen, where you will get first-hand experience in making and tasting local produce. The interactive workshop is scheduled every Thursday to Sunday at 10.30am & 11.30am. No registration needed.");
        trippyEventItem36.setPoint(0L);
        trippyEventItem36.setStartDate(startDate.getTime());
        trippyEventItem36.setEndDate(endDate.getTime());
        trippyEventItem36.setPrice(0.0);
        trippyEventItem36.setEventImage(eventImages);
        trippyEventItem36.setEventType(eventTypes);
        trippyEventItem36.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem36);

        startDate.set(2018, Calendar.JANUARY, 26);
        endDate.set(2019, Calendar.SEPTEMBER, 30);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://4cxqn5j1afk2facwz3mfxg5r-wpengine.netdna-ssl.com/wp-content/uploads/2017/12/Endgame-Nabilah-Nordin-2015-Credit-Chris-Crocker-_-DISINI-1-smaller-e1514515405119.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem37 = new TrippyEventItem();
        trippyEventItem37.setEventName("DISINI by Gillman Barracks & Chan + Hori");
        trippyEventItem37.setEventDescription("DISINI, an inaugural site-specific visual arts festival, comprising a series of public programmes, outdoor sculptures and murals by home-grown, regional and international artists. It celebrates rich heritage of Gillman Barracks as a former military barracks and its current role as Asia’s leading contemporary arts cluster. Highlights include captivating outdoor artworks located across various spaces within the precinct. There will be a multi-functional artist-designed pavilion where a series of exciting and stimulating programmes will take place, with curatorial-led showcases to capture visitors’ attention.");
        trippyEventItem37.setPoint(0L);
        trippyEventItem37.setStartDate(startDate.getTime());
        trippyEventItem37.setEndDate(endDate.getTime());
        trippyEventItem37.setPrice(0.0);
        trippyEventItem37.setEventImage(eventImages);
        trippyEventItem37.setEventType(eventTypes);
        trippyEventItem37.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem37);

        startDate.set(2018, Calendar.AUGUST, 2);
        endDate.set(2019, Calendar.JUNE, 9);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://4cxqn5j1afk2facwz3mfxg5r-wpengine.netdna-ssl.com/wp-content/uploads/2018/06/Singapore-River-1962-e1530081235216.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem38 = new TrippyEventItem();
        trippyEventItem38.setEventName("Lim Cheng Hoe: Painting Singapore");
        trippyEventItem38.setEventDescription("Step into the past and see the beautiful evolution of Singapore’s landscape between the 1930s and 1970s at this exhibition by Lim Cheng Hoe. Discover over 60 works, from idyllic rural villages and vibrant boat traffic on the Singapore river, to portraits and still life. As one of Singapore’s pioneer artists and a leading watercolour artist of his time, you definitely don’t want to miss Lim’s exhibition.");
        trippyEventItem38.setPoint(0L);
        trippyEventItem38.setStartDate(startDate.getTime());
        trippyEventItem38.setEndDate(endDate.getTime());
        trippyEventItem38.setPrice(0.0);
        trippyEventItem38.setEventImage(eventImages);
        trippyEventItem38.setEventType(eventTypes);
        trippyEventItem38.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem38);

        startDate.set(2018, Calendar.AUGUST, 1);
        endDate.set(2018, Calendar.OCTOBER, 29);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://4cxqn5j1afk2facwz3mfxg5r-wpengine.netdna-ssl.com/wp-content/uploads/2018/07/Bath%E6%B2%9091x122cm-e1531888302586.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem39 = new TrippyEventItem();
        trippyEventItem39.setEventName("The Nanyang Space Tour Exhibition");
        trippyEventItem39.setEventDescription("Over 30 stunning artworks by award-winning artist Tan Ruirong will be on display at Pan Pacific Singapore. Tan combines western painting techniques and strong oriental hues that resemble Chinese calligraphy, allowing viewers to appreciate beauty of Chinese characters. Admire his series of Nanyang style paintings on display, exploring space, lines and colours.");
        trippyEventItem39.setPoint(0L);
        trippyEventItem39.setStartDate(startDate.getTime());
        trippyEventItem39.setEndDate(endDate.getTime());
        trippyEventItem39.setPrice(0.0);
        trippyEventItem39.setEventImage(eventImages);
        trippyEventItem39.setEventType(eventTypes);
        trippyEventItem39.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem39);

        startDate.set(2018, Calendar.JULY, 29);
        endDate.set(2018, Calendar.DECEMBER, 31);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://4cxqn5j1afk2facwz3mfxg5r-wpengine.netdna-ssl.com/wp-content/uploads/2018/07/Quayside-Sunday-Floral-Market_4-e1531447641363.jpeg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem40 = new TrippyEventItem();
        trippyEventItem40.setEventName("Quayside's Sunday Floral Market");
        trippyEventItem40.setEventDescription("Admire and purchase beautiful bespoke flower arrangements at this pop-up flower stall. Using a wide range of seasonal blooms including lilies, peonies, tulips and more, these are insta-worthy and sure to make your friends jealous. You can also take part in a DIY potpourri station or floral arrangement workshops to create your own bouquets. Hello Flowers will be at Quayside every last Sunday of the month from 11am to 4pm.Those visiting the floral market can follow #FlowersatQuayside to stay updated.");
        trippyEventItem40.setPoint(0L);
        trippyEventItem40.setStartDate(startDate.getTime());
        trippyEventItem40.setEndDate(endDate.getTime());
        trippyEventItem40.setPrice(0.0);
        trippyEventItem40.setEventImage(eventImages);
        trippyEventItem40.setEventType(eventTypes);
        trippyEventItem40.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem40);

        startDate.set(2018, Calendar.SEPTEMBER, 1);
        endDate.set(2018, Calendar.OCTOBER, 19);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2016/10/8060fa-1855-3rd-Charter-of-Justice.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem41 = new TrippyEventItem();
        trippyEventItem41.setEventName("Law of the Land: Highlights of Singapore's Constitutional Documents");
        trippyEventItem41.setEventDescription("This exhibition introduces the rich history of Singapore’s constitutional development from its founding as a British settlement in 1819 to its emergence as a sovereign republic in 1965. Discover rare constitutional documents from the library and archives, each capturing a key moment in Singapore’s legal history and journey to independence.");
        trippyEventItem41.setPoint(0L);
        trippyEventItem41.setStartDate(startDate.getTime());
        trippyEventItem41.setEndDate(endDate.getTime());
        trippyEventItem41.setPrice(0.0);
        trippyEventItem41.setEventImage(eventImages);
        trippyEventItem41.setEventType(eventTypes);
        trippyEventItem41.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem41);

        startDate.set(2018, Calendar.DECEMBER, 12);
        endDate.set(2018, Calendar.DECEMBER, 31);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("http://static.asiawebdirect.com/m/phuket/portals/www-singapore-com/homepage/attractions/asian-civilizations-museum/pagePropertiesImage/asian-civilisations-museum-singapore.jpg.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem42 = new TrippyEventItem();
        trippyEventItem42.setEventName("Ancient Religions");
        trippyEventItem42.setEventDescription("The ACM permanent galleries on Level 2 explore how artists have masterfully expressed complex ideas about life and existence with religions in sculpture and paintings. Beginning with the Ancient Religions exhibition, which explores early styles and motifs of Buddhism, Hinduism, and Jainism in India and how they spread to China and the larger Southeast Asia, the story continues in the following galleries as the art developed and evolved through the centuries.");
        trippyEventItem42.setPoint(0L);
        trippyEventItem42.setStartDate(startDate.getTime());
        trippyEventItem42.setEndDate(endDate.getTime());
        trippyEventItem42.setPrice(0.0);
        trippyEventItem42.setEventImage(eventImages);
        trippyEventItem42.setEventType(eventTypes);
        trippyEventItem42.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem42);

        startDate.set(2018, Calendar.MAY, 5);
        endDate.set(2019, Calendar.FEBRUARY, 3);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/04/Amek-Gambar_Peranakans-and-Photography.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem43 = new TrippyEventItem();
        trippyEventItem43.setEventName("Amek Gambar: Peranakans and Photography");
        trippyEventItem43.setEventDescription("The Peranakan Museum will be presenting its first historical exhibition showcasing one of the world’s largest collection of Peranakan photography. Through more than 200 photographs and portraits, the exhibition traces the history and evolution of photography in the region, with a focus on how the Peranakan community captured and projected themselves to the world through the multi-faceted medium of photographs. Besides catching a glimpse of the personal and social lives of the Peranakans, Amek Gambar: Peranakans and Photography will bring visitors back in time to when photography was first invented and arrived in Singapore.");
        trippyEventItem43.setPoint(0L);
        trippyEventItem43.setStartDate(startDate.getTime());
        trippyEventItem43.setEndDate(endDate.getTime());
        trippyEventItem43.setPrice(0.0);
        trippyEventItem43.setEventImage(eventImages);
        trippyEventItem43.setEventType(eventTypes);
        trippyEventItem43.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem43);

        startDate.set(2018, Calendar.JUNE, 27);
        endDate.set(2018, Calendar.SEPTEMBER, 2018);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/04/Facebook-banner-Simbakneel-17Aug.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem44 = new TrippyEventItem();
        trippyEventItem44.setEventName("The Lion King");
        trippyEventItem44.setEventDescription("More than 95 million people around the world have watched The Lion King and now, this well-loved musical has returned to Marina Bay Sands. Winner of over 70 major international theatre awards, this celebrated musical brings together one of the most innovative and creative teams on Broadway. Brilliantly re-imagined by acclaimed director Julie Taymor, Disney’s beloved film is transformed into a spectacular showcase that will redefine the theatrical experience. The Lion King also features some of world’s most recognisable music, composed by multi-award winning artists Elton John and Tim Rice.  ");
        trippyEventItem44.setPoint(0L);
        trippyEventItem44.setStartDate(startDate.getTime());
        trippyEventItem44.setEndDate(endDate.getTime());
        trippyEventItem44.setPrice(65.0);
        trippyEventItem44.setEventImage(eventImages);
        trippyEventItem44.setEventType(eventTypes);
        trippyEventItem44.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem44);

        startDate.set(2018, Calendar.JULY, 21);
        endDate.set(2018, Calendar.SEPTEMBER, 30);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/06/b84a09-Honeycombers_DSC8158_cropped.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem45 = new TrippyEventItem();
        trippyEventItem45.setEventName("Trees of Life Exhibition: Knowledge In Material");
        trippyEventItem45.setEventDescription("NTU Centre for Contemporary Art Singapore is embarking on an inquiry into natural materials, exploring the knowledge they embody as biological forms as well as within social, geopolitical, and historical contexts. Trees of Life – Knowledge in Material is part of the Centre’s long-term research cluster CLIMATES. HABITATS. ENVIRONMENTS.\n"
                + "This exhibition focuses on four plants deeply rooted in Asia: indigo, lacquer, rattan, and mulberry. The works trace the ongoing involvement with the highlighted plants in the artistic practices of Manish Nai with indigo, Phi Phi Oanh with lacquer, Sopheap Pich with rattan, and Liang Shaoji and Vivian Xu with mulberry silk. While the featured installations serve as a starting point to uncover the materiality of the chosen plants, the study of their natural and cultural DNA allows further exploration into their biological processes and diverse usages.");
        trippyEventItem45.setPoint(0L);
        trippyEventItem45.setStartDate(startDate.getTime());
        trippyEventItem45.setEndDate(endDate.getTime());
        trippyEventItem45.setPrice(0.0);
        trippyEventItem45.setEventImage(eventImages);
        trippyEventItem45.setEventType(eventTypes);
        trippyEventItem45.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem45);

        startDate.set(2017, Calendar.AUGUST, 17);
        endDate.set(2018, Calendar.SEPTEMBER, 30);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/08/A.I-Ready_webanner_R1.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem46 = new TrippyEventItem();
        trippyEventItem46.setEventName("A.I Ready @ Science Centre Singapore");
        trippyEventItem46.setEventDescription("Jointly organised by the Agency for Science, Technology & Research (A*STAR) and Science Centre Singapore, A.I Ready @ Science Centre Singapore invites any and everyone who loves to inquire, imagine and invent to explore the latest scientific developments. This year’s theme of A.I. You Ready explores artificial intelligence and how machines can learn like humans.\n"
                + "This September, head to the Science Centre Singapore to find out how to be A.I. ready. Learning coding, meet the creator of Singapore’s first digital influencer, our favourite chatbot, Bus Uncle. Gather the fam and work together to complete A.I. tasks, games and activities in the A.I. You Ready gallery trail and redeem a special token.\n"
                + "Be sure to check out the other impressive science events happening across Singapore including Maker Faire at Our Tampines Hub, Star Lecture on The Language of Life at Mediacorp, Science Buskers at Plaza Singapura, Science Shows at Science Centre Singapore and X-Periment at one-north.");
        trippyEventItem46.setPoint(0L);
        trippyEventItem46.setStartDate(startDate.getTime());
        trippyEventItem46.setEndDate(endDate.getTime());
        trippyEventItem46.setPrice(0.0);
        trippyEventItem46.setEventImage(eventImages);
        trippyEventItem46.setEventType(eventTypes);
        trippyEventItem46.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem46);
        
        startDate.set(2018, Calendar.AUGUST, 25);
        endDate.set(2018, Calendar.SEPTEMBER, 23);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/08/2e5848-Honeycombers-Image.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem47 = new TrippyEventItem();
        trippyEventItem47.setEventName("FX HARSONO | REMINISCENCE");
        trippyEventItem47.setEventDescription("Seminal Indonesian artist FX Harsono presents ‘Reminiscence’, the artist’s longstanding major project that investigates the genocide and mass graves of ethnic Chinese-Indonesians in Java, Indonesia from 1947 to 1949. Part documentary and part commemoration, the exhibition will see a new series of drawings alongside two major installations that confronts the truth of this history.");
        trippyEventItem47.setPoint(0L);
        trippyEventItem47.setStartDate(startDate.getTime());
        trippyEventItem47.setEndDate(endDate.getTime());
        trippyEventItem47.setPrice(0.0);
        trippyEventItem47.setEventImage(eventImages);
        trippyEventItem47.setEventType(eventTypes);
        trippyEventItem47.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem47);
        
        startDate.set(2018, Calendar.AUGUST, 30);
        endDate.set(2018, Calendar.SEPTEMBER, 30);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/08/9bf19e-rsz_dsc_4327-min-2.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem48 = new TrippyEventItem();
        trippyEventItem48.setEventName("Vivocity and Disney Tsum Tsum Mid-Autumn Festival of Love");
        trippyEventItem48.setEventDescription("This Mid-Autumn, VivoCity and The Walt Disney Company Southeast Asia invite all Singaporeans to come together to celebrate the love of family and friends and a month long of Disney magic under the stars with their Disney Tsum Tsum Mid-Autumn Celebration of Love campaign. Held from 30 August to 30 September 2018 at VivoCity Sky Park Level 3, this Mid-Autumn celebration is a visual spectacle and a sight to behold, where over 2,000 Tsum Tsum themed lanterns will light up the night sky. As one of Asia’s largest lantern installations, guests can expect nights of revelry and festivities with their favourite Disney and Disney-Pixar Tsum Tsum characters.");
        trippyEventItem48.setPoint(0L);
        trippyEventItem48.setStartDate(startDate.getTime());
        trippyEventItem48.setEndDate(endDate.getTime());
        trippyEventItem48.setPrice(0.0);
        trippyEventItem48.setEventImage(eventImages);
        trippyEventItem48.setEventType(eventTypes);
        trippyEventItem48.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem48);
        
        startDate.set(2018, Calendar.AUGUST, 31);
        endDate.set(2018, Calendar.OCTOBER, 7);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/08/e7404c-Exhibition-compressed.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem49 = new TrippyEventItem();
        trippyEventItem49.setEventName("Indonesia: Land of Treasures Exhibition");
        trippyEventItem49.setEventDescription("The Land of Treasures Exhibition features majestic large-scale displays capturing the beauty and essence of cities and islands of Indonesia, right at Changi Airport. Featuring iconic landscapes from Labuan Bajo, Surabaya and more, come experience the ever-intriguing charm of Indonesia as you foray into local culture and adventures, all in one place!\n" +
"Key highlights of the exhibition:\n" +
"• Come face-to-face with a replica Komodo Dragon at Labuan Bajo, home to the Komodo National Park Cave, the only remaining natural habitat where the endangered Komodo Dragon still runs wild;\n" +
"• Check out the spectacular landscapes of Belitung through interactive binoculars at a look-out point high above the exhibition;\n" +
"• Be inspired by the ancient bells of the world’s largest Buddhist temple and UNESCO World Heritage-listed Borobudur Temple in Yogyakarta;\n" +
"• Round up your visit at the Instagram-worthy swing floating above crystal clear waters, against the backdrop of an amazing Lombok sunset.");
        trippyEventItem49.setPoint(0L);
        trippyEventItem49.setStartDate(startDate.getTime());
        trippyEventItem49.setEndDate(endDate.getTime());
        trippyEventItem49.setPrice(0.0);
        trippyEventItem49.setEventImage(eventImages);
        trippyEventItem49.setEventType(eventTypes);
        trippyEventItem49.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem49);
        
        startDate.set(2018, Calendar.SEPTEMBER, 2);
        endDate.set(2018, Calendar.NOVEMBER, 11);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/08/742ca9-Lan-Zhenghui_E04_930-x-550_honeycombers.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem50 = new TrippyEventItem();
        trippyEventItem50.setEventName("Crossfades and Drawn Forms: Golnaz Fathi and Lan ZhengHui");
        trippyEventItem50.setEventDescription("Reinterpreting traditional art forms, Iranian artist Golnaz Fathi’s free abstractions of the written word coalesce with Chinese Lan Zhenghui’s monumental ink landscapes at Pearl Lam Galleries. A phenomenal transpiration of the essence of art making, the artists conflate Eastern and Western ideals, cementing their place at the forefront of Asian contemporary art.");
        trippyEventItem50.setPoint(0L);
        trippyEventItem50.setStartDate(startDate.getTime());
        trippyEventItem50.setEndDate(endDate.getTime());
        trippyEventItem50.setPrice(0.0);
        trippyEventItem50.setEventImage(eventImages);
        trippyEventItem50.setEventType(eventTypes);
        trippyEventItem50.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem50);
        
        startDate.set(2018, Calendar.SEPTEMBER, 6);
        endDate.set(2018, Calendar.OCTOBER, 28);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/08/fdb6cf-honeycombers.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem51 = new TrippyEventItem();
        trippyEventItem51.setEventName("STREET 2");
        trippyEventItem51.setEventDescription("To celebrate its first year in Singapore, Mazel Galerie presents Street2 a group show featuring new works from Mazel Galerie’s established and upcoming street artists: stencil artists ill, C215, and MONK, street-pop artist Laurina Paperina, muralist NOIR, and multi-media street artist Fidia Falaschetti.\n" +
"Each artist will have a message of their own to share with the audience. From MONK’s camouflage series, bringing awareness to endangered animals, to iLL’s stencil works showcasing some of today’s social contradictions, and C215 colourful animal portraits.");
        trippyEventItem51.setPoint(0L);
        trippyEventItem51.setStartDate(startDate.getTime());
        trippyEventItem51.setEndDate(endDate.getTime());
        trippyEventItem51.setPrice(0.0);
        trippyEventItem51.setEventImage(eventImages);
        trippyEventItem51.setEventType(eventTypes);
        trippyEventItem51.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem51);
        
        startDate.set(2018, Calendar.SEPTEMBER, 8);
        endDate.set(2018, Calendar.OCTOBER, 8);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("http://www.visitsingapore.com/editorials/whats-happening-in-singapore/_jcr_content/par/mobile_21_content_sl/sliderccpar1/editorial_generic_co/content/item_5.thumbnail.image-path1.350.197.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem52 = new TrippyEventItem();
        trippyEventItem52.setEventName("Mid-Autumn Festival Light-Up");
        trippyEventItem52.setEventDescription("Singaporeans celebrate the Mid-Autumn Festival by rolling\n" +
" out lanterns in a flurry of forms and mooncakes (sweet\n" +
" traditional pastries) in a multitude of flavours and textures.\n" +
" Come admire the moon at its brightest, along with the deluge \n" +
"of decorations that will enliven Chinatown.");
        trippyEventItem52.setPoint(0L);
        trippyEventItem52.setStartDate(startDate.getTime());
        trippyEventItem52.setEndDate(endDate.getTime());
        trippyEventItem52.setPrice(0.0);
        trippyEventItem52.setEventImage(eventImages);
        trippyEventItem52.setEventType(eventTypes);
        trippyEventItem52.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem52);
        
        startDate.set(2018, Calendar.SEPTEMBER, 7);
        endDate.set(2018, Calendar.SEPTEMBER, 24);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/08/c99760-Street-Bazaar-1-01.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem53 = new TrippyEventItem();
        trippyEventItem53.setEventName("Chinatown Mid-Autumn Festival 2018");
        trippyEventItem53.setEventDescription("More than 200 Street vendors offering a wide array of festive must haves and treats like mooncake, tea, and decorative ornaments.");
        trippyEventItem53.setPoint(0L);
        trippyEventItem53.setStartDate(startDate.getTime());
        trippyEventItem53.setEndDate(endDate.getTime());
        trippyEventItem53.setPrice(0.0);
        trippyEventItem53.setEventImage(eventImages);
        trippyEventItem53.setEventType(eventTypes);
        trippyEventItem53.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem53);
        
        startDate.set(2018, Calendar.SEPTEMBER, 7);
        endDate.set(2018, Calendar.SEPTEMBER, 30);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/09/f543bf-BalvenieCoC-7-copy.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem54 = new TrippyEventItem();
        trippyEventItem54.setEventName("The Balvenie House of Craft");
        trippyEventItem54.setEventDescription("Specially curated by craft purveyors The General Company, The Balvenie House of Craft is a first-ever pop-up event showcasing local craftsmen and artisans. The open house comprises showcases and installations, hands-on craft workshops, supper club dinners, open bar and complimentary whisky tasting, film screenings and community talks. Notable participating craftsmen include marquage painter Cherin Sim, embroidery artist Teresa Lim, hand-lettering artist Ewe Jin Tee, floral artist Josephine Lau, among many others.");
        trippyEventItem54.setPoint(0L);
        trippyEventItem54.setStartDate(startDate.getTime());
        trippyEventItem54.setEndDate(endDate.getTime());
        trippyEventItem54.setPrice(0.0);
        trippyEventItem54.setEventImage(eventImages);
        trippyEventItem54.setEventType(eventTypes);
        trippyEventItem54.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem54);
        
        startDate.set(2018, Calendar.SEPTEMBER, 18);
        endDate.set(2018, Calendar.SEPTEMBER, 22);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/06/0d20db-eventsubmission-copy.jpg");
        eventTypes.add(art_and_culture);

        TrippyEventItem trippyEventItem55 = new TrippyEventItem();
        trippyEventItem55.setEventName("Shakespeare's Globe Tour: Iconic Plays and Audience's Choice Performances");
        trippyEventItem55.setEventDescription("Shakespeare’s Globe is returning to Singapore this 18 – 22 September with three iconic plays – Twelfth Night, The Merchant Of Venice and The Taming Of The Shrew – and Audience Choice shows. The Audience Choice show is a first for Shakespeare’s Globe where the audience can choose what they want to watch. During the last two shows on 22nd September, the company of eight actors will let the audience vote between the three plays, just like how theatre companies would go on tour back in Shakespeare’s days.");
        trippyEventItem55.setPoint(0L);
        trippyEventItem55.setStartDate(startDate.getTime());
        trippyEventItem55.setEndDate(endDate.getTime());
        trippyEventItem55.setPrice(88.0);
        trippyEventItem55.setEventImage(eventImages);
        trippyEventItem55.setEventType(eventTypes);
        trippyEventItem55.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem55);

        // Initialising the events for foodie
        
        startDate.set(2018, Calendar.SEPTEMBER, 21);
        endDate.set(2018, Calendar.SEPTEMBER, 23);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("http://www.visitsingapore.com/editorials/whats-happening-in-singapore/_jcr_content/par/mobile_21_content_sl/sliderccpar1/editorial_generic_co/content/item_14.thumbnail.image-path1.350.197.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem56 = new TrippyEventItem();
        trippyEventItem56.setEventName("Singapore Tea Festival");
        trippyEventItem56.setEventDescription("With overwhelming support at last year’s debut of Singapore Tea Festival (STF), the second edition of STF will be back this September! Proudly organised by teapasar, the first global omni-channel marketplace featuring local and international tea brands, this year’s STF will be held from 21st – 23rd September at Basement 4, ION Orchard.\n" +
"This year’s STF is bigger and better with 28 exciting tea and tea-related brands, and 3 days worth of educational and engaging workshops to look forward to! From TIMELESS CLASSICS featuring single-origin tea merchants such as Tea Chapter and Parchmen Academy, to MODERN BLENDS by many of Singapore’s very own tea brands with interesting blends inspired by local flavours such as Durian Tea (SUCRE) and Nyonya Kaya (The 1872 Clipper Tea Co.); to Botanically Cold Brewed (TM) Sparkling Teas (Gryphon Tea), and even 100% compostable tea pods that are also compatible with Nespresso® machines (A.muse Projects). This year’s STF will also feature INTERNATIONAL FLAVOURS such as Matcha soft serve (Matchaya), and teas direct from Japan (Ito En) and Indonesia (Bali Organic Tea). There will also be LIFESTYLE-related merchandise – from quirky tea illustrations by Troops On Print, to hand-crafted ceramics by Euphoramics and &Natural.");
        trippyEventItem56.setPoint(0L);
        trippyEventItem56.setStartDate(startDate.getTime());
        trippyEventItem56.setEndDate(endDate.getTime());
        trippyEventItem56.setPrice(0.0);
        trippyEventItem56.setEventImage(eventImages);
        trippyEventItem56.setEventType(eventTypes);
        trippyEventItem56.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem56);
        
        startDate.set(2018, Calendar.SEPTEMBER, 27);
        endDate.set(2018, Calendar.SEPTEMBER, 30);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://tgff.com.sg/assets/images/logo.png");
        eventImages.add("http://www.visitsingapore.com/editorials/whats-happening-in-singapore/_jcr_content/par/mobile_21_content_sl/sliderccpar1/editorial_generic_co/content/item_9.thumbnail.image-path1.350.197.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem57 = new TrippyEventItem();
        trippyEventItem57.setEventName("The GREAT Food Festival");
        trippyEventItem57.setEventDescription("Check out this one-stop gastronomic platform to indulge in the most delectable offerings with our celebrity chefs and patissiers. Be inspired by live demonstrations and perhaps pick up some tricks of the trade from hands-on masterclasses.");
        trippyEventItem57.setPoint(0L);
        trippyEventItem57.setStartDate(startDate.getTime());
        trippyEventItem57.setEndDate(endDate.getTime());
        trippyEventItem57.setPrice(30.0);
        trippyEventItem57.setEventImage(eventImages);
        trippyEventItem57.setEventType(eventTypes);
        trippyEventItem57.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem57);
        
        startDate.set(2018, Calendar.SEPTEMBER, 20);
        endDate.set(2018, Calendar.SEPTEMBER, 21);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/08/a56df7-CM-Mid-Autumn-2018-Honeycombers.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem58 = new TrippyEventItem();
        trippyEventItem58.setEventName("COMO Marketplace - Mid-Autumn Festival 2018");
        trippyEventItem58.setEventDescription("Join us for an evening of traditional treats and a family-friendly lantern parade at COMO Dempsey. Be treated to handcrafted mooncakes and artisan tea in the lush garden, and end the beautiful evening with a lantern parade and a stroll around Dempsey under the moonlight. Be sure to bring your best lantern for a chance to bag the ‘Best Lantern of the Night’ prize.");
        trippyEventItem58.setPoint(0L);
        trippyEventItem58.setStartDate(startDate.getTime());
        trippyEventItem58.setEndDate(endDate.getTime());
        trippyEventItem58.setPrice(0.0);
        trippyEventItem58.setEventImage(eventImages);
        trippyEventItem58.setEventType(eventTypes);
        trippyEventItem58.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem58);
        
        startDate.set(2018, Calendar.SEPTEMBER, 26);
        endDate.set(2018, Calendar.SEPTEMBER, 27);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/08/7e1faf-Untitled-design-4.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem59 = new TrippyEventItem();
        trippyEventItem59.setEventName("Food of the Future Festival - Asia's first Experimental Food Event");
        trippyEventItem59.setEventDescription("Asia’s FIRST Experiential Food Event – Taste, Feel & Learn about the Future of Food.\n" +
"\n" +
"Immerse yourself in the future of food with a curated marketplace, hands-on experience zones & empowering speaker keynotes thoughtfully put together to fill up your well-deserved Saturday!\n" +
"\n" +
"There will be workshops for kids and parents for a hands-on experience such as building your own hydroponic farm and sustainable beekeeping.\n" +
"\n" +
"On top of that, there will also be a vibrant marketplace for you to taste and buy some futures foods home!");
        trippyEventItem59.setPoint(0L);
        trippyEventItem59.setStartDate(startDate.getTime());
        trippyEventItem59.setEndDate(endDate.getTime());
        trippyEventItem59.setPrice(0.0);
        trippyEventItem59.setEventImage(eventImages);
        trippyEventItem59.setEventType(eventTypes);
        trippyEventItem59.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem59);
        
        startDate.set(2018, Calendar.NOVEMBER, 18);
        endDate.set(2018, Calendar.NOVEMBER, 20);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2016/11/047d7e-2016-11-03-PHOTO-00000304-930x550.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem60 = new TrippyEventItem();
        trippyEventItem60.setEventName("Food Rebel Fridays");
        trippyEventItem60.setEventDescription("Calling All Food Rebels! Your favourite organic and clean living cafe in the CBD just got better. Join us for some Friday rebellion as Kitchen By Food Rebel adds world class organic & bio-dynamic wines, gluten free craft beer and tapas dining to its menu. From November 18th we’ll be extending our opening hours every Friday until 8pm giving wine lovers and clean living fans a chance to enjoy our healthy food just that little bit longer.");
        trippyEventItem60.setPoint(0L);
        trippyEventItem60.setStartDate(startDate.getTime());
        trippyEventItem60.setEndDate(endDate.getTime());
        trippyEventItem60.setPrice(0.0);
        trippyEventItem60.setEventImage(eventImages);
        trippyEventItem60.setEventType(eventTypes);
        trippyEventItem60.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem60);
        
        startDate.set(2018, Calendar.SEPTEMBER, 26);
        endDate.set(2018, Calendar.SEPTEMBER, 27);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/09/1f6b10-AustrianWineDinner_Web-930x550.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem61 = new TrippyEventItem();
        trippyEventItem61.setEventName("Austrian Wine Dinner at P.S Cafe");
        trippyEventItem61.setEventDescription("Immerse yourself in the future of food with a curated marketplace, hands-on experience zones & empowering speaker keynotes thoughtfully put together to fill up your well-deserved Saturday!");
        trippyEventItem61.setPoint(0L);
        trippyEventItem61.setStartDate(startDate.getTime());
        trippyEventItem61.setEndDate(endDate.getTime());
        trippyEventItem61.setPrice(0.0);
        trippyEventItem61.setEventImage(eventImages);
        trippyEventItem61.setEventType(eventTypes);
        trippyEventItem61.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem61);
        
        startDate.set(2018, Calendar.APRIL, 1);
        endDate.set(2018, Calendar.DECEMBER, 31);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/03/8cf50c-1920x500_pb_alacartemenu-930x500.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem62 = new TrippyEventItem();
        trippyEventItem62.setEventName("New Flavours at Peach Blossoms");
        trippyEventItem62.setEventDescription("Executive Chinese Chef Edward Chong introduces a new à la carte menu at Peach Blossoms. Signature dishes include Flambéed Chinese Wine Spring Chicken, Roasted Tomahawk Steak in \"Xinjiang\" Style accompanied with Green Papaya Salad, Tom Yum Soup with \"Otak\" served in Whole Coconut and Chilled Abalone with Konbu and Jelly Fish served with Yuzu Sauce.");
        trippyEventItem62.setPoint(0L);
        trippyEventItem62.setStartDate(startDate.getTime());
        trippyEventItem62.setEndDate(endDate.getTime());
        trippyEventItem62.setPrice(51.0);
        trippyEventItem62.setEventImage(eventImages);
        trippyEventItem62.setEventType(eventTypes);
        trippyEventItem62.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem62);
        
        startDate.set(2018, Calendar.JULY, 25);
        endDate.set(2018, Calendar.SEPTEMBER, 6);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/07/e0283d-1-min.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem63 = new TrippyEventItem();
        trippyEventItem63.setEventName("Marina Mandarin's Mid-Autumn Delights");
        trippyEventItem63.setEventDescription("There will be workshops for kids and parents for a hands-on experience such as building your own hydroponic farm and sustainable beekeeping.");
        trippyEventItem63.setPoint(0L);
        trippyEventItem63.setStartDate(startDate.getTime());
        trippyEventItem63.setEndDate(endDate.getTime());
        trippyEventItem63.setPrice(64.0);
        trippyEventItem63.setEventImage(eventImages);
        trippyEventItem63.setEventType(eventTypes);
        trippyEventItem63.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem63);
        
        startDate.set(2018, Calendar.JULY, 25);
        endDate.set(2018, Calendar.DECEMBER, 31);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/07/cb3145-3-min.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem64 = new TrippyEventItem();
        trippyEventItem64.setEventName("Heritage High Tea at the Atrium Lounge");
        trippyEventItem64.setEventDescription("Be spoilt for choice and pamper yourself with a delectable array of Asian and Western high tea treats served on a three-tier stand at the Atrium Lounge.");
        trippyEventItem64.setPoint(0L);
        trippyEventItem64.setStartDate(startDate.getTime());
        trippyEventItem64.setEndDate(endDate.getTime());
        trippyEventItem64.setPrice(38.0);
        trippyEventItem64.setEventImage(eventImages);
        trippyEventItem64.setEventType(eventTypes);
        trippyEventItem64.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem64);
        
        startDate.set(2018, Calendar.SEPTEMBER, 1);
        endDate.set(2018, Calendar.SEPTEMBER, 30);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/07/3b4526-4-min.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem65 = new TrippyEventItem();
        trippyEventItem65.setEventName("MARGARITA MADNESS");
        trippyEventItem65.setEventDescription("On top of that, there will also be a vibrant marketplace for you to taste and buy some futures foods home!");
        trippyEventItem65.setPoint(0L);
        trippyEventItem65.setStartDate(startDate.getTime());
        trippyEventItem65.setEndDate(endDate.getTime());
        trippyEventItem65.setPrice(18.0);
        trippyEventItem65.setEventImage(eventImages);
        trippyEventItem65.setEventType(eventTypes);
        trippyEventItem65.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem65);
        
        startDate.set(2018, Calendar.SEPTEMBER, 10);
        endDate.set(2018, Calendar.SEPTEMBER, 14);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/09/ff7d57-zmSOAQ0Q-2.jpeg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem66 = new TrippyEventItem();
        trippyEventItem66.setEventName("A PROVENANCE-LED GASTRONOMIC EXPERIENCE");
        trippyEventItem66.setEventDescription("An Exclusive Four Hands Collaboration at Origin Grill featuring Winner of MasterChef Brazil Professionals 2017 Pablo Oazen & Heidi Flanagan. Guest Chef Pablo and hosting Chef de Cuisine Heidi Flanagan will present an exclusive 5-day gourmet collaboration at Origin Grill. Focusing on provenance, both chefs will create a gastronomic fare featuring curated, unique and quality-driven ingredients sourced from local and regional producers as well as from Brazil.");
        trippyEventItem66.setPoint(0L);
        trippyEventItem66.setStartDate(startDate.getTime());
        trippyEventItem66.setEndDate(endDate.getTime());
        trippyEventItem66.setPrice(34.0);
        trippyEventItem66.setEventImage(eventImages);
        trippyEventItem66.setEventType(eventTypes);
        trippyEventItem66.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem66);
        
        startDate.set(2018, Calendar.SEPTEMBER, 11);
        endDate.set(2018, Calendar.SEPTEMBER, 17);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/07/4ba059-2-min.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem67 = new TrippyEventItem();
        trippyEventItem67.setEventName("REV UP YOUR APPETITE THIS RACE SEASON AT AQUAMARINE");
        trippyEventItem67.setEventDescription("Go full speed ahead with AquaMarine’s buffet, featuring international specials like the Australian Slow-roasted OP Beef Rib with Chimichurri, the Sicilian Whitefish En Papillote, and the Moroccan Leg of Lamb Shawarma with Pita or Flat Bread.​");
        trippyEventItem67.setPoint(0L);
        trippyEventItem67.setStartDate(startDate.getTime());
        trippyEventItem67.setEndDate(endDate.getTime());
        trippyEventItem67.setPrice(60.0);
        trippyEventItem67.setEventImage(eventImages);
        trippyEventItem67.setEventType(eventTypes);
        trippyEventItem67.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem67);
        
        startDate.set(2018, Calendar.SEPTEMBER, 11);
        endDate.set(2018, Calendar.SEPTEMBER, 17);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/07/bfce87-7-min.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem68 = new TrippyEventItem();
        trippyEventItem68.setEventName("HAPPY HOUR JUST GOT HAPPIER");
        trippyEventItem68.setEventDescription("With happy hour extended to 12 noon to 8pm, you can now get a midday pick-me-up with our variety of house pours at only $12++ per drink at the Atrium Lounge!");
        trippyEventItem68.setPoint(0L);
        trippyEventItem68.setStartDate(startDate.getTime());
        trippyEventItem68.setEndDate(endDate.getTime());
        trippyEventItem68.setPrice(12.0);
        trippyEventItem68.setEventImage(eventImages);
        trippyEventItem68.setEventType(eventTypes);
        trippyEventItem68.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem68);
        
        startDate.set(2018, Calendar.SEPTEMBER, 13);
        endDate.set(2018, Calendar.OCTOBER, 23);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/09/671f34-WhatsApp-Image-2018-09-04-at-6.18.33-PM.jpeg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem69 = new TrippyEventItem();
        trippyEventItem69.setEventName("CELEBRATE OKTOBERFEST THE AUTHENTIC GERMAN WAY AT BROTZEIT!");
        trippyEventItem69.setEventDescription("Everyone’s favourite German festival is just around the corner, and Brotzeit’s got heaps of fun-filled activities, traditional entertainment, food and booze lined up for you this Oktoberfest!");
        trippyEventItem69.setPoint(0L);
        trippyEventItem69.setStartDate(startDate.getTime());
        trippyEventItem69.setEndDate(endDate.getTime());
        trippyEventItem69.setPrice(0.0);
        trippyEventItem69.setEventImage(eventImages);
        trippyEventItem69.setEventType(eventTypes);
        trippyEventItem69.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem69);
        
        startDate.set(2018, Calendar.SEPTEMBER, 14);
        endDate.set(2018, Calendar.SEPTEMBER, 16);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/08/229164-AngiesOysterBar_Exterior_01-copy-930x550.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem70 = new TrippyEventItem();
        trippyEventItem70.setEventName("THE LANDMARK CIRCUIT: GLOBAL PITSTOPS");
        trippyEventItem70.setEventDescription("As Marina Bay becomes the Mediterranean Sea, transport yourself to the glitz and glamour of Monaco at the lavish Oyster Festival. With the décor and mouthwatering delights inspired by the decadent French Riviera hot spot, guests can fuel up on an ‘All You Can Eat’ dinner of seafood favourites from Angie’s signature Oysters to pots of Mussels & Clams.");
        trippyEventItem70.setPoint(0L);
        trippyEventItem70.setStartDate(startDate.getTime());
        trippyEventItem70.setEndDate(endDate.getTime());
        trippyEventItem70.setPrice(118.0);
        trippyEventItem70.setEventImage(eventImages);
        trippyEventItem70.setEventType(eventTypes);
        trippyEventItem70.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem70);
        
        startDate.set(2018, Calendar.SEPTEMBER, 15);
        endDate.set(2018, Calendar.SEPTEMBER, 16);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/07/amberlounge_honeycombers.jpeg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem71 = new TrippyEventItem();
        trippyEventItem71.setEventName("AMBER LOUNGE SINGAPORE GRAND PRIX VIP PARTY 2018");
        trippyEventItem71.setEventDescription("The exotic island of Singapore, home to Formula 1’s first ever night race is one of Amber Lounge’s most provocative and glamorous destinations. A celebrity and F1 driver hotspot, Amber Lounge Singapore merges the euphoric party spirit of the West with the splendour of the Orient.");
        trippyEventItem71.setPoint(0L);
        trippyEventItem71.setStartDate(startDate.getTime());
        trippyEventItem71.setEndDate(endDate.getTime());
        trippyEventItem71.setPrice(500.0);
        trippyEventItem71.setEventImage(eventImages);
        trippyEventItem71.setEventType(eventTypes);
        trippyEventItem71.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem71);
        
        startDate.set(2018, Calendar.OCTOBER, 1);
        endDate.set(2018, Calendar.OCTOBER, 30);
        eventImages.clear();
        eventTypes.clear();
        eventImages.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2018/07/ddbc56-5-min.jpg");
        eventTypes.add(foodie);

        TrippyEventItem trippyEventItem72 = new TrippyEventItem();
        trippyEventItem72.setEventName("PERANAKAN DELIGHTS");
        trippyEventItem72.setEventDescription("This October, indulge in hearty Peranakan fare at AquaMarine! Blending Chinese ingredients with Malay or Indonesian spices, succulent highlights include Udang Karang Goreng Assam*, Nyonya Spiced Ayam Shawarma with Lotus Bun, Salmon Fillet coated with Otah-Otah Mousse, and Nyonya Itek Sioh.");
        trippyEventItem72.setPoint(0L);
        trippyEventItem72.setStartDate(startDate.getTime());
        trippyEventItem72.setEndDate(endDate.getTime());
        trippyEventItem72.setPrice(60.0);
        trippyEventItem72.setEventImage(eventImages);
        trippyEventItem72.setEventType(eventTypes);
        trippyEventItem72.setSoftDelete(false);

        trippyEventSessionLocal.createTrippyEvent(trippyEventItem72);
        
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
