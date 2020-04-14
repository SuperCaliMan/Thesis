private static MutableLiveData<List<DayDetail>> daysList = new MutableLiveData<>();
    /**
     * @param year
     * @param month [0-11]
     * @return tutti i giorni del mese
     */
    public LiveData<List<DayDetail>> getDays(int year,int month){
        new AsyncTaskGetDays(year,month,timeWebApi).execute();
        return daysList;
    }
    
    private static class AsyncTaskGetDays extends AsyncTask<Void,Void,Void>{
        private int year,month;
        private List<DayDetail> res;
        private TimeWebApi timeWebApi;

        public AsyncTaskGetDays(int year, int month,TimeWebApi timeWebApi) {
            this.year = year;
            this.month = month;
            this.timeWebApi = timeWebApi;
        }

        @Override
        protected Void doInBackground(Void... voids) {
           res = timeWebApi.getDayDetails(year,month); //chiamata ai web service 
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            daysList.postValue(res); //modifico il live data postando il nuovo valore di res
        }
}