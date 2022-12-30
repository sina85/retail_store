/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package retailstore;

import com.db.DbConnection;
import java.sql.Connection;

public class RetailStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DbConnection.getConnection();
    }
    
}
