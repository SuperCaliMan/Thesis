@Database(entities = {OrderSerialized.class},version = 1,exportSchema = false)
public abstract class OrdersDatabaseRoom extends RoomDatabase {
    private static OrdersDatabaseRoom INSTANCE;
    
    public static OrdersDatabaseRoom getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OrdersDatabaseRoom.class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            OrdersDatabaseRoom.class, "OrdersDB")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    
    public abstract OrdersDao ordersDao(); //riferimento al dao
}
