package Util;

public class UtilIMDB {
    public static final String[] IMDB_ID = new String[]{
            "tt5691226",
            "tt6160932",
            "tt2007972",
            "tt1729652",
            "tt1999819",
            "tt2055577",
            "tt0268446",
            "tt1519638",
            "tt0305406",
            "tt3696800",
            "tt0304487",
            "tt2792440",
            "tt5225580",
            "tt5595416",
            "tt3342558",
            "tt2296799",
            "tt0304435",
            "tt0277967",
            "tt2007972",
            "tt0303719",
            "tt2179133",
            "tt1884254",
            "tt0315394",
            "tt5659988",
            "tt2353801",
            "tt3700482",
            "tt4991660",
            "tt4123886",
            "tt1977865",
            "tt1522265",
            "tt1729652",
            "tt5225580",
            "tt2923296",
            "tt3768406",
            "tt2094059",
            "tt2007994",
            "tt2408026",
            "tt1161732",
            "tt2007973",
            "tt2980794",
            "tt2098630",
            "tt2705130",
            "tt2532814",
            "tt1987567",
            "tt2102353",
            "tt1867612",
            "tt2147361",
            "tt3187718",
            "tt5804178",
            "tt6501192",
            "tt4620528",
            "tt4624138",
            "tt6329870",
            "tt6501286",
            "tt0888045",
            "tt4448446",
            "tt6044190",
            "tt6419748",
            "tt5954644",
            "tt0299180",
            "tt3768406",
            "tt0210727",
            "tt4208620",
            "tt5972072",
            "tt3638644",
            "tt6149934",
            "tt3908994",
            "tt5743300",
            "tt2166508",
            "tt0405121",
            "tt0342015",
            "tt6352664",
            "tt6249904",
            "tt5973366",
            "tt0452803",
            "tt0301653",
            "tt0304469",
            "tt0300971",
            "tt0316930",
            "tt3552320",
            "tt0382764",
            "tt0314362",
            "tt0304067",
            "tt0301907",
            "tt0313240",
            "tt2368945",
            "tt5093808",
            "tt0305692",
            "tt1505367",
            "tt1956577",
            "tt1999911",
            "tt0392779",
            "tt5302258",
            "tt5955818",
            "tt6352592",
            "tt5642582",
            "tt2438106",
            "tt6440150",
            "tt5252250",
            "tt6453622",
            "tt0325215",
            "tt6044656",
            "tt0485092",
            "tt0301835",
            "tt3521084",
            "tt3451716",
//            "tt0301471",
            "tt0396498",
            "tt4361472",
            "tt3746250",
            "tt4599430",
            "tt1999130",
            "tt5179156",
            "tt4567392",
            "tt3856476",
            "tt3541976",
            "tt4604268",
            "tt0927739",
            "tt0411490",
            "tt3755616",
            "tt2543854",
            "tt2315946",
            "tt2573214",
            "tt2087866",
            "tt5187396",
            "tt1447951",
            "tt1213587",
            "tt2543820",
            "tt1507392",
            "tt1507965",
            "tt4899194",
            "tt2474992",
            "tt0985673",
            "tt2514384",
            "tt0460746",
            "tt1051238",
            "tt3103326",
            "tt1494660",
            "tt2233588",
            "tt3609728",
//            "tt2808236",
            "tt0416188",
//            "tt6098792",
            "tt5646492",
            "tt6501310",
//            "tt6511860",
            "tt6023632",
            "tt1604591",
            "tt6091496",
            "tt5126250"
    };

    public static String GET_API_URL(String id){
        //https://www.omdbapi.com/?i=tt3103326&apikey=832f855c
//        String api_key = "832f855c";
//        String api_key = "57ee2a22";
//        String api_key = "a7833ae6";
        String api_key = "167d6266";
        return "https://www.omdbapi.com/?i="+id+"&apikey="+api_key;
    }
}
