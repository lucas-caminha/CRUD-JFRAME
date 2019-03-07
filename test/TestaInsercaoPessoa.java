/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import model.HibernateUtil;
import model.Pessoa;
import org.hibernate.Session;

/**
 *
 * @author Lucas - Admin
 */
public class TestaInsercaoPessoa {
    
    public static void main(String[] args) {
        
        
        Pessoa p1 = new Pessoa("Lucas", 19);
        
        
        Session ss = HibernateUtil.getSessionFactory().openSession();
        
        ss.getTransaction().begin();
        
        ss.persist(p1);
        ss.getTransaction().commit();
        ss.close();
        

        
    }
    
}
