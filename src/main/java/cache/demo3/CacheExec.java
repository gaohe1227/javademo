package cache.demo3;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月24日
 *
 * 作用:自定义缓存的测试
 */
public class CacheExec {
 
    public static void main(String[] args) {
        // TODO 自動生成されたメソッド?スタブ
//        String province = request.getParameter("province");
//        String city= request.getParameter("city");
//        String county= request.getParameter("county");
//        Document doc = XMLBuilder.buildLatelyKeyword(kwm.latelyKeyword(province, city, county));
//        out.write(doc);
        String province = "Jiangsu ";
        String city= "Nanjing ";
        String county= "Jiangning";
        CacheOperation co = CacheOperation.getInstance();
//        MethodInfo mi = co.new MethodInfo(kwm, "latelyKeyword", new Object[]{province, city, county});
//        Document doc = (Document )co.getCacheData(XMLBuilder.class,"buildLatelyKeyword",new Object[]{mi}, 120000, 0);
//        out.write(doc);
       while (true){
            // chf@tsinghua.org.cn 
            MethodInfo mi = new MethodInfo(AddrDetail.class, "latelyKeyword", new Object[]{province, city, county});
            // 120000 毫秒（2分钟）更新缓存
            String aa = (String)co.getCacheData(AddrDetail.class,"buildCache",new Object[]{mi}, 120000, 5);
            System.out.println("CacheExec:main=" + aa);
        }    }
}
