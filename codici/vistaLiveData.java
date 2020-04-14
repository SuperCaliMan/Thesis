  homeViewModel.getDays()
                .observe(getViewLifecycleOwner(), new Observer<List<DayDetail>>() {
                    @Override
                    public void onChanged(List<DayDetail> list){
                        UpdateUI()
                        ...
                    }});