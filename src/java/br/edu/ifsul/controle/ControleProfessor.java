/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.dao.ProfessorDAO;
import br.edu.ifsul.modelo.Professor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alexv
 */
@ManagedBean(name = "controleProfessor")
@SessionScoped
public class ControleProfessor implements Serializable {
    
    private ProfessorDAO dao;
    private Professor objeto;
    private EspecialidadeDAO daoEspecialidade;
    private AlunoDAO daoAluno;
    
    public ControleProfessor(){
        dao = new ProfessorDAO();
        daoEspecialidade = new EspecialidadeDAO();
        daoAluno = new AlunoDAO();
    }
    
    public String listar(){
        return "/privado/professor/listar?faces-redirect=true";
    }
    
    public String novo(){
        setObjeto(new Professor());
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        if (getDao().salvar(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(getDao().getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        setObjeto(getDao().localizar(id));
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        setObjeto(getDao().localizar(id));
        if (getDao().remover(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public ProfessorDAO getDao() {
        return dao;
    }

    public void setDao(ProfessorDAO dao) {
        this.dao = dao;
    }

    public Professor getObjeto() {
        return objeto;
    }

    public void setObjeto(Professor objeto) {
        this.objeto = objeto;
    }

    public EspecialidadeDAO getDaoEspecialidade() {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDAO daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }

    public AlunoDAO getDaoAluno() {
        return daoAluno;
    }

    public void setDaoAluno(AlunoDAO daoAluno) {
        this.daoAluno = daoAluno;
    }


}