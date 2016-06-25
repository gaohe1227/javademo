package cache.demo3;

public class AddrDetail {
	public String latelyKeyword(String province, String city, String county){
        System.out.println("AddrDetail.latelyKeyword=" + province + city + county);
        return province + city + county;
    }
    public String buildCache(String latelyKeyword){
        System.out.println("AddrDetail.buildCache=" + latelyKeyword);
        return latelyKeyword;
    }
}
