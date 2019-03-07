/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.Controller;
import dao.PessoaDAO;
import view.PessoaView;

/**
 *
 * @author Lucas - Admin
 */
public class Execute {
    
    public static void main(String[] args) {
        
        PessoaView pessoaView = new PessoaView();
        PessoaDAO pessoaDao = new PessoaDAO();
        Controller controller = new Controller(pessoaView, pessoaDao);
        pessoaView.setVisible(true);
        pessoaView.setLocationRelativeTo(null);
        
    }
    
}
