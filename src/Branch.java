import java.util.ArrayList;

public class Branch {
    private String branchName;
    private ArrayList<Customer> customerList;

    public Branch(String branchName, ArrayList<Customer> customerList) {
        this.branchName = branchName;
        this.customerList = new ArrayList<Customer>();
    }

    public boolean addCustomer (String name, double newDeposit){
        ArrayList<Double> transactions=new ArrayList<Double>();
        transactions.add(newDeposit);
    Customer newCustomer= new Customer(name,transactions);
    if (findCustomer(name)>=0) return false;
    customerList.add(newCustomer);
    return true;

    }

    public boolean addTransaction (String name, double transaction) {
        int position=findCustomer(name);
        if (position<0) return false;
        customerList.get(position).getTransactions().add(transaction);
        return true;
    }

    public void printCustomers(){
        System.out.println("The list of Customers is " );
        double total=0;
        for (int i=0;i<customerList.size();i++){
            System.out.println((i+1)+". " + customerList.get(i).getName()+ " with transactions");
            total=0;
            for (int j=0;j<customerList.get(i).getTransactions().size();j++){
                System.out.println("------------  "+ customerList.get(i).getTransactions().get(j));
                total+=customerList.get(i).getTransactions().get(j);
            }
            System.out.println("TOTAL for Customer\t"+total);
        }
    }


    private int findCustomer (String name){
        for (int i=0;i<customerList.size();i++){
            if (this.customerList.get(i).getName().equals(name)) return i;
        }
        return -1;
    }

    public String getBranchName() {
        return branchName;
    }
}
