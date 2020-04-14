@Entity
public class OrderSerialized implements Serializable {
    @PrimaryKey @NonNull
    private String id;

    @ColumnInfo(name = "Commessa")
    private String cmi01JobOrderSerialized;
    ... getter e setter
}
