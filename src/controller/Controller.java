/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PessoaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Pessoa;
import view.PessoaView;

/**
 *
 * @author Lucas - Admin
 */
public class Controller implements ActionListener, KeyListener{
    
    PessoaView pessoaView = new PessoaView();
    PessoaDAO pessoaDao = new PessoaDAO();
    
    public Controller(PessoaView pv, PessoaDAO pd){
        this.pessoaView = pv;
        this.pessoaDao = pd;
        this.pessoaView.btnAdd.addActionListener(this);
        this.pessoaView.btnEditar.addActionListener(this);
        this.pessoaView.btnExcluir.addActionListener(this);
        this.pessoaView.btnListar.addActionListener(this);
        this.pessoaView.btnOk.addActionListener(this);
    }
    
    // Limpa elementos da tabela
    public void limpaElementos(){
        pessoaView.txtIdade.setText("");
        pessoaView.txtID.setText("");
        pessoaView.txtNome.setText("");
    }
    
    
    // Preenche a tabela da view Pessoa (jtDados)
    public void preencheTabela(JTable tabela){
        
        try{
        DefaultTableModel tabelinha = new DefaultTableModel();
        tabela.setModel(tabelinha);
        tabelinha.addColumn("Id");
        tabelinha.addColumn("Nome");
        tabelinha.addColumn("Idade");
        Object [] coluna  = new Object[3];
        int numRegistros = pessoaDao.listaPessoas().size();
        for(int i = 0; i<numRegistros; i++){
            coluna [0] = pessoaDao.listaPessoas().get(i).getId();
            coluna[1] = pessoaDao.listaPessoas().get(i).getNome();
            coluna[2] = pessoaDao.listaPessoas().get(i).getIdade();
            tabelinha.addRow(coluna);
        }
        
        
        }catch(Exception exc){
            System.out.println("Erro: "+exc.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Adicionar
        if(e.getSource() == pessoaView.btnAdd){
             String nome = pessoaView.txtNome.getText();
             Integer idade = Integer.parseInt(pessoaView.txtIdade.getText());
             Pessoa pessoa = new Pessoa(nome, idade);
             pessoaDao.inserePessoa(pessoa);
         }
        
        // Editar
        if(e.getSource() == pessoaView.btnEditar){
            int filaEditar = pessoaView.jtDados.getSelectedRow();
            int numFS = pessoaView.jtDados.getSelectedRowCount();
            if(filaEditar >= 0 && numFS == 1){
                pessoaView.txtID.setText(String.valueOf(pessoaView.jtDados.getValueAt(filaEditar, 0)));
                pessoaView.txtNome.setText(String.valueOf(pessoaView.jtDados.getValueAt(filaEditar, 1)));
                pessoaView.txtIdade.setText(String.valueOf(pessoaView.jtDados.getValueAt(filaEditar, 2)));
                pessoaView.btnAdd.setEnabled(false);
                pessoaView.btnExcluir.setEnabled(false);
                pessoaView.btnListar.setEnabled(false);
                pessoaView.btnEditar.setEnabled(false);          
            } else {
                JOptionPane.showMessageDialog(null,"Selecione uma Linha!");
            }    
        }
        
        if(e.getSource() == pessoaView.btnOk){          
            int id = Integer.parseInt(pessoaView.txtID.getText());
            String nome = pessoaView.txtNome.getText();
            int idade = Integer.parseInt(pessoaView.txtIdade.getText());
            
            Pessoa pessoa = new Pessoa(nome, idade);
            pessoaDao.editaPessoa(id, pessoa);
            
            limpaElementos();
            
            pessoaView.btnAdd.setEnabled(true);
            pessoaView.btnExcluir.setEnabled(true);
            pessoaView.btnListar.setEnabled(true);
            pessoaView.btnEditar.setEnabled(true);                  
        }
        
        if(e.getSource() == pessoaView.btnExcluir){
            int filainicio = pessoaView.jtDados.getSelectedRow();
            Integer id;
            
            id = Integer.parseInt(pessoaView.jtDados.getValueAt(filainicio, 0).toString());
            Pessoa pessoa = new Pessoa();
            pessoa.setId(id);
            pessoaDao.removePessoa(pessoa, id);
            preencheTabela(pessoaView.jtDados);                 
        }   
       
        if(e.getSource() == pessoaView.btnListar){
            preencheTabela(pessoaView.jtDados);
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
