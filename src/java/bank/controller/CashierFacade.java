package bank.controller;


import bank.model.Currency;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 * A controller. All calls to the model that are executed because of an action taken by
 * the cashier pass through here.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class CashierFacade {
    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

   public float findCurrency(int amount,String inputCurrency,String outputCurrency) {
        System.out.println("beforedatabasefindcall");
        String input = inputCurrency+"to"+outputCurrency;
        System.out.println(input);
        Currency found =  em.find(Currency.class,input);
        if (found == null) {
            throw new EntityNotFoundException("no exchange rate " + "USDtoEUR");
        }
        System.out.println("readsuccessfullyfromdabatase"+found.rates());
        return amount*found.rates();
    }

    

    

}
