package br.edu.iftm.jsf.bean;

import br.edu.iftm.jsf.entity.Produto;
import br.edu.iftm.jsf.logic.ProdutoLogic;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.file.UploadedFile;

@Named
@SessionScoped
public class ProdutoBean extends GenericBean<Produto, ProdutoLogic> {

    @Inject
    private ProdutoLogic logic;

    @Getter @Setter
    private UploadedFile foto;

    private final String diretorio = System.getProperty("user.home") + File.separator + "fotos";

    @Override
    public ProdutoLogic getLogic() {
        return logic;
    }

    @Override
    public Class<Produto> getClassEntity() {
        return Produto.class;
    }

    @Override
    public void salvar() {
        String arquivoSalvo = salvarArquivo();
        if(arquivoSalvo != null) {
            getEntity().setFotoDisco(arquivoSalvo);
        }
        super.salvar();
    }

    public String salvarArquivo() {
        if(foto == null){
            return null;
        }
        try {
            String nomeFoto = foto.getFileName();
            String arquivo = "foto"+new Date().getTime()+nomeFoto.substring(nomeFoto.lastIndexOf("."), nomeFoto.length());
            File fotoDisco = new File(diretorio, arquivo);
            OutputStream os = new FileOutputStream(fotoDisco);
            os.write(foto.getContent());
            os.flush();
            os.close();
            return fotoDisco.getAbsolutePath();
        } catch (IOException ex) {
            addError("Erro ao carregar foto. " + ex.getMessage());
        }
        return null;
    }

}
