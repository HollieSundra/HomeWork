public class User extends Player {

    User(String handName) {
        super(handName);
    }
    private int wallet;

    public void setFunds(int wallet){

        this.wallet = wallet;
    }

    public void addFunds(int addAmount){

        wallet = wallet + addAmount;
    }

    public void subtractFunds(int subtractAmount){

        wallet = wallet + subtractAmount;
    }
    public int totalFunds(){

        return wallet;
    }
}

// create own methods to get, add, subtract