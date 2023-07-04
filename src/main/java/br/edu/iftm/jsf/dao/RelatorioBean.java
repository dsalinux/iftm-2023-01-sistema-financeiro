/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.iftm.jsf.dao;

import br.edu.iftm.jsf.util.BeanUtil;
import br.edu.iftm.jsf.util.MimeTypes;
import br.edu.iftm.jsf.util.ReportUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.hibernate.jdbc.Work;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.SerializableSupplier;

/**
 *
 * @author danilo
 */
@Named
@SessionScoped
public class RelatorioBean extends BeanUtil {

    private StreamedContent file;

    @Inject
    private EntityManager em;

    @Getter @Setter
    private String nome;
    
    public StreamedContent getFile() {
        SerializableSupplier s = new SerializableSupplier() {
            @Override
            public Object get() {
                return new ByteArrayInputStream(gerarRelatorio());
            }
        };
        file = DefaultStreamedContent.builder()
                .name("produtos.pdf")
                .contentType(MimeTypes.MIME_APPLICATION_PDF)
                .stream(() -> {
                        return (InputStream) new ByteArrayInputStream(gerarRelatorio());
                        })
                .build();
        return file;
    }

    public byte[] gerarRelatorio() {
        try {
            InputStream reportInputStream = getClass().getResourceAsStream("/report/listaProdutos.jrxml");

            HashMap<String, Object> map = new HashMap<>();
            Session hibernateSession = em.unwrap(Session.class);

            hibernateSession.doWork((Connection connection) -> {
                map.put("REPORT_CONNECTION", connection);
            });
            map.put("NOME", nome);
//            map.put("REPORT_CONNECTION", null);

            return ReportUtil.reportToPDF(null, reportInputStream, map);
        } catch (Exception ex) {
            Logger.getLogger(RelatorioBean.class.getName()).log(Level.SEVERE, null, ex);
            addError("Erro ao gerar relatório. " + ex.getMessage());
            return null;
        }
    }
}
