@Dao
public interface OrdersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) 
    void insertFavoriteOrder(OrderSerialized order);

    @Read
    LiveData<List<OrderSerialized>> getAllFavoriteOrders();

    @Update
    int updateFavoriteOrder(OrderSerialized order);

    @Delete
    void deleteFavoriteOrder(OrderSerialized order);
    
    @Query(SELECT COUNT(*) FROM OrderSerialized)
    int dbSize();
}
