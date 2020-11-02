package service.core;

import java.util.LinkedList;

public class ClientApplication {

    public ClientApplication() {

    }

    private int id;
    private ClientInfo client;
    private LinkedList<Quotation> quotes;
   


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setClient( ClientInfo client) {

        this.client = client;

    }

    public ClientInfo getClient() {
        return client;
    }

    public void setList() {
        this.quotes = new LinkedList<Quotation>();
    }

    public void addQoute(Quotation quote) {

        quotes.add(quote);
    }

    public LinkedList<Quotation> getQuotes() {
        return quotes;
    }
    

    
}
