package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {
    
    /**
     * @Description:  根据rid和uid查询收藏
     * @Date:  2019/8/25
     * @Param: [rid, uid]
     * @return:  cn.itcast.travel.domain.Favorite
     */
    public Favorite findByRidAndUid(int rid,int uid);

    /**
     * @Description:  根据rid查询收藏次数
     * @Date:  2019/8/25
     * @Param: [rid]
     * @return:  int
     */
    int findCountByRid(int rid);

    /**
     * @Description:  添加收藏
     * @Date:  2019/8/25
     * @Param: [parseInt, uid]
     * @return:  void
     */
    void add(int parseInt, int uid);
}
