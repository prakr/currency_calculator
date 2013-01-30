package bank.view;

import bank.controller.CashierFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("acctManager")
@ConversationScoped
public class AcctManager implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB
    private CashierFacade cashierFacade;
    private String convertFrom;
    private String convertTo;
    private Integer amountToConvert;
    private float finalMoney;
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }

    /**
     * Returns <code>false</code> if the latest transaction failed, otherwise <code>false</code>.
     */
    public boolean getSuccess() {
        return transactionFailure == null;
    }

    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
        return transactionFailure;
    }
    
    public void findCurrency() {
        try {
            startConversation();
            transactionFailure = null;
            System.out.println("beforefindcurrencycall");
            System.out.println(amountToConvert);
            System.out.println(convertFrom);
            System.out.println(convertTo);
            finalMoney = cashierFacade.findCurrency(amountToConvert,convertFrom,convertTo);
            System.out.println("in accountmgroutput");
            System.out.println(finalMoney);
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Set the value of finalMoney
     *
     * @param finalMoney new value of finalMoney
     */
    public void setFinalMoney(float finalMoney) {
        this.finalMoney = finalMoney;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public float getFinalMoney() {
        return finalMoney;
    }

    /**
     * Set the value of amountToConvert
     *
     * @param amountToConvert new value of newAccountBalance
     */
    public void setAmountToConvert(Integer amountToConvert) {
        this.amountToConvert = amountToConvert;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public Integer getAmountToConvert() {
        return null;
    }

    /**
     * Set the value of convertTo
     *
     * @param convertTo new value of newAccountHolderLastName
     */
    public void setConvertTo(String convertTo) {
        this.convertTo = convertTo;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getConvertTo() {
        return null;
    }

    /**
     * Set the value of convertFrom
     *
     * @param convertFrom new value of newAccountHolderFirstName
     */
    public void setConvertFrom(String convertFrom) {
        this.convertFrom = convertFrom;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getConvertFrom() {
        return null;
    }

   
    
}
