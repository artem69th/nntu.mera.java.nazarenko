import java.io.Serializable;

public class TextMessage implements Serializable {

    private String userName, operation;
    private Double firstValue, secondValue, result;

    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String un)
    {
        userName = un;
    }

    public String getOperation()
    {
        return operation;
    }
    public void setOperation(String operat) { operation = operat; }

    public Double getFirstValue()
    {
        return firstValue;
    }
    public void setFirstValue(Double firstVal)
    {
        firstValue = firstVal;
    }

    public Double getSecondValue()
    {
        return secondValue;
    }
    public void setSecondValue(Double secVal)
    {
        secondValue = secVal;
    }

    public Double getResult()
    {
        return result;
    }
    public void setResult(Double res)
   {
       result = res;
   }

}

