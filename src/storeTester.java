/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andre_000
 */
public class storeTester {
    public static void main(String[] args){
        
        Store newStore = new Store();
        
        Catalog newCatalog = new Catalog();
        
        newCatalog = newStore.createCatalogFromFile();
        
        newCatalog.printCatalog();
    }
}
