public class LoginViewModel extends AndroidViewModel {
    private UserRepository mRepo;
    private Context context;

    //vengono lasciati pubblici per permettere di utilizzare i setter e getter tramite il bind con la vista
    public MutableLiveData<String> username,password,error = new MutableLiveData<String>(""); 
    public MutableLiveData<Boolean> rememberUser = new MutableLiveData<>(false);

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mRepo = new UserRepository(application); //collegamento con il repository
        this.context = application.getApplicationContext();
    }

    /**
     * Permette di eseguire il login di un utente
     */
    public void onLoginClick(){
        String username = username.getValue();
        String password = password.getValue();
        Boolean chk = rememberUser.getValue();
        Employee employee;

        if(username!=null&&password!=null) {
            loginUser(employee,chk, new Response<Boolean>() {
                @Override
                public void onSuccess(Boolean response) {
                    if(response){ 
                        startActivity(context);
                    }else{
                        //live data per gestire gli errori
                        error.postValue(context.getString(R.string.accesso_fallito));
                    }
                }
                @Override
                public void onError(String response) {
                    error.postValue(context.getString(R.string.no_internet_connection));
                }
            });
        }
    }

    /**
     * Eseguo effettivamente l'operazione di login
     * @param employee utente che si vuole loggare
     * @param saveUser salvo l'utente?
     * @param response callback per gestire la risposta dell'operazione
     */
    private void loginUser(Employee employee, Boolean saveUser, Response<Boolean> response){
        mRepo.loginUser(employee,saveUser,response);
    }

    private startActivity(Context context){
        ... //codice per avviare l'activity della home
    }
}
