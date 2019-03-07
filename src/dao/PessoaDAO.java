/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.persistence.EntityManager;
import model.HibernateUtil;
import model.Pessoa;
import org.hibernate.Hibernate;
import javax.persistence.Query;
import model.JPAUtil;
import org.hibernate.Session;

/**
 *
 * @author Lucas - Admin
 */
public class PessoaDAO {
    
    
    public boolean inserePessoa(Pessoa pessoa){
        
        try{
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
             
        sessao.persist(pessoa);
        
        sessao.getTransaction().commit();
        sessao.close();
        return true;
        }catch(Exception exc){
            System.out.println("(InserePessoa)Exceção: "+exc.getMessage());
            return false;
        } 
    }
    
    public boolean editaPessoa(Integer id, Pessoa pessoa){
        
        try{
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            
            Object persistenceInstance = sessao.load(Pessoa.class, id);
         
            if(persistenceInstance != null){
                pessoa.setId(id);
                sessao.update(pessoa);
            }
           
            sessao.getTransaction().commit();
            sessao.close();
            return true;
        }catch(Exception exc){
            System.out.println("(EditaPessoa)Exceção: "+exc.getMessage());
            return false;
        }
        
    }
    
    public boolean removePessoa(Pessoa pessoa, Integer id){
        
        try{
            Session sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            sessao.beginTransaction();
            
            Object persistenceInstance = sessao.load(Pessoa.class,id);
            if(persistenceInstance != null){
                sessao.delete(persistenceInstance);
            }             
            sessao.getTransaction().commit();
            sessao.close();
            return true;
        }catch(Exception exc){
            System.out.println("(RemovePessoa)Exceção: "+exc.getMessage());
            return false;
        }     
    }
    
    public List<Pessoa> listaPessoas() throws Exception{
        
        
        try{
        EntityManager em = new JPAUtil().getEntityManager();
        
        em.getTransaction().begin();
        
        List<Pessoa> resultados = em.createQuery
        ("FROM " + Pessoa.class.getName()).getResultList();
             
        for (Pessoa resultado : resultados) {
            System.out.println("ID: "+resultado.getId());
            System.out.println("Nome: "+resultado.getNome());
            System.out.println("Idade: "+resultado.getIdade());
        }
           
        
        em.getTransaction().commit();
        em.close();
        return resultados;
        }catch(Exception exc){
            System.out.println("(ListaPessoas)Exceção: "+exc.getMessage());
            throw new Exception();
        }  
    }
          


    
    
    
    
}
