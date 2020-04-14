public class UserRepository{
    private TimeWebApi timeWebApi;
    
    public UserRepository(Application application) {
        timeWebApi =  TimeWebApi.getInstance(application.getBaseContext());
    }

    /* Effettuo il login di un nuovo utente
     * @param employee utente da autenticare
     * @param savedUser true se voglio salvare le credenziali */
    public void loginUser(Employee employee, boolean savedUser,Response<Boolean> response){
        if(savedUser) {
            timeWebApi.savePreferences(employee.getUsername(), employee.getPassword());
        }
        new AsyncTaskLogin(employee, timeWebApi,response).execute();
    }

    /*AsyncTask creo un thread per eseguire le chiamate ai web service*/
    private static class AsyncTaskLogin extends AsyncTask<Void,Void,Void>{
        private Employee employee;
        private Boolean res;
        private TimeWebApi timeWebApi;
        private Response<Boolean> callback;

        public AsyncTaskLogin(Employee employee,TimeWebApi timeWebApi, Response<Boolean> callback) {
            this.employee = employee;
            this.timeWebApi =timeWebApi;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            isLoading.postValue(true);
            try {
                res = timeWebApi.login(employee.getUsername(),employee.getPassword());
            }catch (UnknownHostException e){
                callback.onError(e.getMessage());
            }
           return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null && res != null) {
                callback.onSuccess(res);
            }
        }
    }



