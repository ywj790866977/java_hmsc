package cn.itcast.travel.service;

public interface FavoriteService {
    
    /**
     * @Description:  判断收藏
     * @Date:  2019/8/25
     * @Param: [rid, uid]
     * @return:  boolean
     */
    public boolean isFavorite(String rid,int uid);

    /**
     * @Description:  添加收藏
     * @Date:  2019/8/25
     * @Param: [rid, uid]
     * @return:  void
     */
    void add(String rid, int uid);
}
