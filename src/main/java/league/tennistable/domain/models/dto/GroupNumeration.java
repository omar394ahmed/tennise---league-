package league.tennistable.domain.models.dto;

public enum GroupNumeration  {
    A(0) ,B(1) , c(2) , D(3) ;

    public  int  order ;


    GroupNumeration(int order) {
        this.order = order;
    }


}