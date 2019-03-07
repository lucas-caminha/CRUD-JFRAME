
import model.HibernateUtil;
import model.Pessoa;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas - Admin
 */
public class TestaEdicaoPessoa {
    
    public static void main(String[] args) {
        
        Pessoa pessoa = new Pessoa("daniel",20);
        Integer id = 3;
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        Object persistenceInstance = sessao.load(Pessoa.class, id);

        if(persistenceInstance != null){
            pessoa.setId(id);
            sessao.update(pessoa);
        }

        sessao.getTransaction().commit();
        sessao.close();
        
    }
    
}
