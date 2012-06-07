package com.br.managertranschool.architecture.database;

import com.br.managertranschool.business.list.TipoUsuarioList;
import com.br.managertranschool.business.vo.CidadeVO;
import com.br.managertranschool.business.vo.ClienteLocalidadeVO;
import com.br.managertranschool.business.vo.ClienteRotaVO;
import com.br.managertranschool.business.vo.ClienteVO;
import com.br.managertranschool.business.vo.EstadoVO;
import com.br.managertranschool.business.vo.LocalidadeVO;
import com.br.managertranschool.business.vo.PagamentoRealizadoVO;
import com.br.managertranschool.business.vo.PagamentoVO;
import com.br.managertranschool.business.vo.RotaHistoricoVO;
import com.br.managertranschool.business.vo.RotaLocalidadeVO;
import com.br.managertranschool.business.vo.RotaVO;
import com.br.managertranschool.business.vo.UsuarioVO;

import android.content.Context;

/**
 * Classe utilizada para criar ou atualizar a base de dados da aplicação.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 20/05/2012
 */
public class DatabaseCreate {

    public static final String NOME_DATABASE = "manager-tran-school";

    public static final Integer VERSAO_DATABASE = 1;

    /**
     * Construtor utilizando contexto do app como argumento.
     * 
     * @param context - Contexto do app;
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public DatabaseCreate(Context context) {

        SQLiteHelper sqlHelper = new SQLiteHelper(context, NOME_DATABASE, VERSAO_DATABASE, getScriptCreateTables(),
            getScriptDropTables(), getScriptInsert());

        sqlHelper.getWritableDatabase();
        sqlHelper.close();
    }

    /**
     * Obtém usuário do sistema a ser inserido na criação da base.
     * 
     * @return - {@link UsuarioVO}
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private UsuarioVO obterUsuarioSistema() {
        UsuarioVO usuario = new UsuarioVO();
        usuario.setLogin("system");
        usuario.setSenha("systemMTSsystem");
        usuario.setTipoUsuario(TipoUsuarioList.ADMINISTRADOR.getCodigo());
        usuario.setNome("system");

        return usuario;
    }
    
    /**
     * Método obtém script de criação das tabelas da base.
     * 
     * @return Array de scripts.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String[] getScriptCreateTables() {

        return new String[] { this.getCreateTableUsuario(), this.getCreateTableEstado(), this.getCreateTableCidade(),
            this.getCreateTableCliente(), this.getCreateTableLocalidade(), this.getCreateTablePagamento(),
            this.getCreateTablePagamentoRealizado(), this.getCreateTableRota(), this.getCreateTableRotaLocalidade(),
            this.getCreateTableClienteLocalidade(), this.getCreateTableRotaHistorico(),
            this.getCreateTableClienteRota() };
    }

    /**
     * Método obtem script de drop das tabelas que sofrerão mudanças ao atualizar a base.
     * 
     * @return Array de scripts.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String[] getScriptDropTables() {

        return new String[] {};
    }

    /**
     * Método obtem script de insert de dados.
     * 
     * @return Array de scripts.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String[] getScriptInsert() {

        return new String[] { this.getInsertIntoUsuario(), this.getInsertIntoEstado(), this.getInsertIntoCidade() };
    }

    /**
     * Método obtém script de create da tabela USUARIO.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTableUsuario() {

        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(UsuarioVO.TABLE);
        script.append("(");
        script.append(UsuarioVO.ID_USUARIO).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ");
        script.append(UsuarioVO.TX_LOGIN).append(" VARCHAR(12) NOT NULL UNIQUE, ");
        script.append(UsuarioVO.TX_SENHA).append(" VARCHAR(12) NOT NULL, ");
        script.append(UsuarioVO.TIPO_USUARIO).append(" INT NOT NULL, ");
        script.append(UsuarioVO.TX_NOME).append(" VARCHAR(45) NOT NULL ");
        script.append(");");

        return script.toString();
    }

    /**
     * Método obtém script de create da tabela ESTADO.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTableEstado() {

        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(EstadoVO.TABLE);
        script.append("(");
        script.append(EstadoVO.ID_ESTADO).append(" CHAR(2) NOT NULL PRIMARY KEY UNIQUE, ");
        script.append(EstadoVO.TX_DESCRICAO).append(" VARCHAR(45) NOT NULL UNIQUE ");
        script.append(");");

        return script.toString();
    }

    /**
     * Método obtém script de create da tabela CIDADE.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTableCidade() {
        
        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(CidadeVO.TABLE);
        script.append("(");
        script.append(CidadeVO.ID_CIDADE).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ");
        script.append(CidadeVO.TX_DESCRICAO).append(" VARCHAR(45) NOT NULL, ");
        script.append(CidadeVO.ESTADO_ID).append(" CHAR(2) NOT NULL REFERENCES ").append(EstadoVO.TABLE).append(" ( ").append(EstadoVO.ID_ESTADO).append(" ) ");
        script.append(");");
       
        return script.toString();
    }
    
    /**
     * Método obtém script de create da tabela CLIENTE.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTableCliente() {
        
        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(ClienteVO.TABLE);
        script.append("(");
        script.append(ClienteVO.ID_CLIENTE).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ");
        script.append(ClienteVO.TX_NOME).append(" VARCHAR(80) NOT NULL, ");
        script.append(ClienteVO.TX_ENDERECO).append(" VARCHAR(80) NOT NULL, ");
        script.append(ClienteVO.CIDADE_ID).append(" INTEGER NOT NULL REFERENCES ").append(CidadeVO.TABLE).append(" ( ").append(CidadeVO.ID_CIDADE).append(" ), ");
        script.append(ClienteVO.CEP).append(" CHAR(12) NULL, ");
        script.append(ClienteVO.TX_EMAIL).append(" VARCHAR(45) NULL, ");
        script.append(ClienteVO.CPF).append(" VARCHAR(11) NOT NULL UNIQUE, ");
        script.append(ClienteVO.TX_BAIRRO).append(" VARCHAR(45) NOT NULL, ");
        script.append(ClienteVO.NR_TELEFONE_PRIMARIO).append(" VARCHAR(15) NULL, ");
        script.append(ClienteVO.NR_TELEFONE_SECUNDARIO).append(" VARCHAR(15) NULL ");
        script.append(");");
        
        // TODO - Fazer contrant de FK CIDADE.

        return script.toString();
    }
    
    /**
     * Método obtém script de create da tabela LOCALIDADE.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTableLocalidade() {

        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(LocalidadeVO.TABLE);
        script.append("(");
        script.append(LocalidadeVO.ID_LOCALIDADE).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ");
        script.append(LocalidadeVO.NR_LATITUDE).append(" DOUBLE NOT NULL, ");
        script.append(LocalidadeVO.NR_LONGITUDE).append(" DOUBLE NOT NULL, ");
        script.append(LocalidadeVO.TX_DESCRICAO).append(" VARCHAR(45) NOT NULL ");
        script.append(");");

        return script.toString();
    }
    
    /**
     * Método obtém script de create da tabela PAGAMENTO.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTablePagamento() {

        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(PagamentoVO.TABLE);
        script.append("(");
        script.append(PagamentoVO.ID_PAGAMENTO).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ");
        script.append(PagamentoVO.CLIENTE_ID).append(" INTEGER NOT NULL REFERENCES ").append(ClienteVO.TABLE).append(" ( ").append(ClienteVO.ID_CLIENTE).append(" ), ");
        script.append(PagamentoVO.TIPO_PAGAMENTO).append(" INTEGER NOT NULL, ");
        script.append(PagamentoVO.VALOR).append(" DECIMAL(7,2) NOT NULL, ");
        script.append(PagamentoVO.DT_VENCIMENTO).append(" DATETIME NOT NULL ");
        script.append(");");

        // TODO - Fazer contrant de FK CLIENTE.
        
        return script.toString();
    }
    
    /**
     * Método obtém script de create da tabela PAGAMENTO_REALIZADO.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTablePagamentoRealizado() {

        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(PagamentoRealizadoVO.TABLE);
        script.append("(");
        script.append(PagamentoRealizadoVO.ID_PAGAMENTO_REALIZADO).append(
            " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ");
        script.append(PagamentoRealizadoVO.PAGAMENTO_ID).append(" INTEGER NOT NULL REFERENCES ")
            .append(PagamentoVO.TABLE).append(" ( ").append(PagamentoVO.ID_PAGAMENTO).append(" ), ");
        script.append(PagamentoRealizadoVO.DT_PAGAMENTO).append(" DATETIME NOT NULL, ");
        script.append(PagamentoRealizadoVO.MES_ANO_REFERENTE).append(" DATE NOT NULL ");
        script.append(");");

        // TODO - Fazer contrant de FK PAGAMENTO.

        return script.toString();
    }
    
    /**
     * Método obtém script de create da tabela ROTA.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTableRota() {

        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(RotaVO.TABLE);
        script.append("(");
        script.append(RotaVO.ID_ROTA).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ");
        script.append(RotaVO.TX_DESCRICAO).append(" VARCHAR(45) NOT NULL, ");
        script.append(RotaVO.LOCAL_PARTIDA).append(" INTEGER NOT NULL REFERENCES ").append(LocalidadeVO.TABLE).append(" ( ").append(LocalidadeVO.ID_LOCALIDADE).append(" ) ");
        script.append(");");

        // TODO - Fazer contrant de FK LOCAL_PARTIDA - LOCALIDADE.
        
        return script.toString();
    }
    
    /**
     * Método obtém script de create da tabela ROTA_LOCALIDADE.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTableRotaLocalidade() {

        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(RotaLocalidadeVO.TABLE);
        script.append("(");
        script.append(RotaLocalidadeVO.ROTA_ID).append(" INTEGER NOT NULL REFERENCES ").append(RotaVO.TABLE)
            .append(" ( ").append(RotaVO.ID_ROTA).append(" ), ");
        script.append(RotaLocalidadeVO.LOCALIDADE_ID).append(" INTEGER NOT NULL REFERENCES ")
            .append(LocalidadeVO.TABLE).append(" ( ").append(LocalidadeVO.ID_LOCALIDADE).append(" ), ");
        script.append(" PRIMARY KEY (").append(RotaLocalidadeVO.ROTA_ID).append(", ")
            .append(RotaLocalidadeVO.LOCALIDADE_ID).append(")");
        script.append(");");

        // TODO - Fazer contrant de FK LOCALIDADE_ID - LOCALIDADE.
        // TODO - Fazer contrant de FK ROTA_ID - ROTA.

        return script.toString();
    }
    
    /**
     * Método obtém script de create da tabela CLIENTE_LOCALIDADE.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTableClienteLocalidade() {

        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(ClienteLocalidadeVO.TABLE);
        script.append("(");
        script.append(ClienteLocalidadeVO.CLIENTE_ID).append(" INTEGER NOT NULL REFERENCES ").append(ClienteVO.TABLE)
            .append(" ( ").append(ClienteVO.ID_CLIENTE).append(" ), ");
        script.append(ClienteLocalidadeVO.LOCALIDADE_ID).append(" INTEGER NOT NULL REFERENCES ")
            .append(LocalidadeVO.TABLE).append(" ( ").append(LocalidadeVO.ID_LOCALIDADE).append(" ), ");
        script.append(" PRIMARY KEY (").append(ClienteLocalidadeVO.CLIENTE_ID).append(", ")
            .append(ClienteLocalidadeVO.LOCALIDADE_ID).append(")");
        script.append(");");

        // TODO - Fazer contrant de FK LOCALIDADE_ID - LOCALIDADE.
        // TODO - Fazer contrant de FK CLIENTE_ID - CLIENTE.

        return script.toString();
    }
    
    /**
     * Método obtém script de create da tabela ROTA_HISTORICO.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTableRotaHistorico() {

        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(RotaHistoricoVO.TABLE);
        script.append("(");
        script.append(RotaHistoricoVO.ID_ROTA_HISTORICO).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ");
        script.append(RotaHistoricoVO.ROTA_ID).append(" INTEGER NOT NULL REFERENCES ").append(RotaVO.TABLE).append(" ( ").append(RotaVO.ID_ROTA).append(" ), ");
        script.append(RotaHistoricoVO.DT_ROTA).append(" DATETIME NOT NULL, ");
        script.append(RotaHistoricoVO.DURACAO).append(" TIME NULL, ");
        script.append(RotaHistoricoVO.USUARIO_ID).append(" INTEGER NOT NULL ");
        script.append(");");

        // TODO - Fazer contrant de FK ROTA_ID - ROTA.
        // TODO - Fazer contrant de FK USUARIO_ID - USUARIO.
        
        return script.toString();
    }
    
    /**
     * Método obtém script de create da tabela CLIENTE_ROTA.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTableClienteRota() {

        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(ClienteRotaVO.TABLE);
        script.append("(");
        script.append(ClienteRotaVO.CLIENTE_ID).append(" INTEGER NOT NULL REFERENCES ").append(ClienteVO.TABLE).append(" ( ").append(ClienteVO.ID_CLIENTE).append(" ), ");
        script.append(ClienteRotaVO.ROTA_ID).append(" INTEGER NOT NULL REFERENCES ").append(RotaVO.TABLE).append(" ( ").append(RotaVO.ID_ROTA).append(" ), ");
        script.append(" PRIMARY KEY (").append(ClienteRotaVO.CLIENTE_ID).append(", ").append(ClienteRotaVO.ROTA_ID).append(")");
        script.append(");");

        // TODO - Fazer contrant de FK LOCALIDADE_ID - LOCALIDADE.
        // TODO - Fazer contrant de FK CLIENTE_ID - CLIENTE.
        
        return script.toString();
    }
    
    /**
     * Método obtém script de insert da tabela ESTADO com Unidades federativas do Brasil.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getInsertIntoEstado() {

        StringBuilder script = new StringBuilder();
        String defaultInsertEstado = "INSERT INTO " + EstadoVO.TABLE + " (" + EstadoVO.ID_ESTADO + ", " + EstadoVO.TX_DESCRICAO + ")";
        
        script.append(defaultInsertEstado).append(" VALUES ('AC', 'Acre'); ");
        script.append(defaultInsertEstado).append(" VALUES (AL', 'Alagoas'); ");
        script.append(defaultInsertEstado).append(" VALUES ('AP', 'Amapá'); ");
        script.append(defaultInsertEstado).append(" VALUES ('AM', 'Amazonas'); ");
        script.append(defaultInsertEstado).append(" VALUES ('BA', 'Bahia'); ");
        script.append(defaultInsertEstado).append(" VALUES ('CE', 'Ceará'); ");
        script.append(defaultInsertEstado).append(" VALUES ('DF', 'Distrito Federal'); ");
        script.append(defaultInsertEstado).append(" VALUES ('ES', 'Espírito Santo'); ");
        script.append(defaultInsertEstado).append(" VALUES ('GO', 'Goiás'); ");
        script.append(defaultInsertEstado).append(" VALUES ('MA', 'Maranhão'); ");
        script.append(defaultInsertEstado).append(" VALUES ('MT', 'Mato Grosso'); ");
        script.append(defaultInsertEstado).append(" VALUES ('MS', 'Mato Grosso do Sul'); ");
        script.append(defaultInsertEstado).append(" VALUES ('MG', 'Minas Gerais'); ");
        script.append(defaultInsertEstado).append(" VALUES ('PE', 'Pernambuco'); ");
        script.append(defaultInsertEstado).append(" VALUES ('PI', 'Piauí'); ");
        script.append(defaultInsertEstado).append(" VALUES ('RJ', 'Rio de Janeiro'); ");
        script.append(defaultInsertEstado).append(" VALUES ('RN', 'Rio Grande do Norte'); ");
        script.append(defaultInsertEstado).append(" VALUES ('RS', 'Rio Grande do Sul'); ");
        script.append(defaultInsertEstado).append(" VALUES ('RO', 'Rondônia'); ");
        script.append(defaultInsertEstado).append(" VALUES ('RR', 'Roraima'); ");
        script.append(defaultInsertEstado).append(" VALUES ('SC', 'Santa Catarina'); ");
        script.append(defaultInsertEstado).append(" VALUES ('PR', 'Paraná'); ");
        script.append(defaultInsertEstado).append(" VALUES ('PB', 'Paraíba'); ");
        script.append(defaultInsertEstado).append(" VALUES ('PA', 'Pará'); ");
        script.append(defaultInsertEstado).append(" VALUES ('SP', 'São Paulo'); ");
        script.append(defaultInsertEstado).append(" VALUES ('SE', 'Sergipe'); ");
        script.append(defaultInsertEstado).append(" VALUES ('TO', 'Tocantins'); ");

        return script.toString();
    }
    
    /**
     * Método obtém script de insert da tabela CIDADE contendo cidades do Estado da Bahia.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getInsertIntoCidade() {
        StringBuilder script = new StringBuilder();
        String defaultInsertCidade = "INSERT INTO " + CidadeVO.TABLE + " (" + CidadeVO.ID_CIDADE + ", " + CidadeVO.TX_DESCRICAO + ", " + CidadeVO.ESTADO_ID + ")";
                
        script.append(defaultInsertCidade).append(" VALUES  (290010, 'ABAIRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290020, 'ABARE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290030, 'ACAJUTIBA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290035, 'ADUSTINA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290040, 'AGUA FRIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290050, 'ERICO CARDOSO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290060, 'AIQUARA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290070, 'ALAGOINHAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290080, 'ALCOBACA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290090, 'ALMADINA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290100, 'AMARGOSA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290110, 'AMELIA RODRIGUES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290115, 'AMERICA DOURADA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290120, 'ANAGE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290130, 'ANDARAI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290135, 'ANDORINHA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290140, 'ANGICAL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290150, 'ANGUERA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290160, 'ANTAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290170, 'ANTONIO CARDOSO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290180, 'ANTONIO GONCALVES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290190, 'APORA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290195, 'APUAREMA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290200, 'ARACATU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290205, 'ARACAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290210, 'ARACI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290220, 'ARAMARI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290225, 'ARATACA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290230, 'ARATUIPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290240, 'AURELINO LEAL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290250, 'BAIANOPOLIS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290260, 'BAIXA GRANDE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290265, 'BANZAE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290270, 'BARRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290280, 'BARRA DA ESTIVA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290290, 'BARRA DO CHOCA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290300, 'BARRA DO MENDES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290310, 'BARRA DO ROCHA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290320, 'BARREIRAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290323, 'BARRO ALTO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290327, 'BARROCAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290330, 'BARRO PRETO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290340, 'BELMONTE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290350, 'BELO CAMPO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290360, 'BIRITINGA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290370, 'BOA NOVA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290380, 'BOA VISTA DO TUPIM', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290390, 'BOM JESUS DA LAPA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290395, 'BOM JESUS DA SERRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290400, 'BONINAL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290405, 'BONITO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290410, 'BOQUIRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290420, 'BOTUPORA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290430, 'BREJOES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290440, 'BREJOLANDIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290450, 'BROTAS DE MACAUBAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290460, 'BRUMADO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290470, 'BUERAREMA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290475, 'BURITIRAMA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290480, 'CAATIBA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290485, 'CABACEIRAS DO PARAGUACU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290490, 'CACHOEIRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290500, 'CACULE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290510, 'CAEM', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290515, 'CAETANOS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290520, 'CAETITE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290530, 'CAFARNAUM', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290540, 'CAIRU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290550, 'CALDEIRAO GRANDE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290560, 'CAMACAN', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290570, 'CAMACARI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290580, 'CAMAMU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290590, 'CAMPO ALEGRE DE LOURDES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290600, 'CAMPO FORMOSO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290610, 'CANAPOLIS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290620, 'CANARANA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290630, 'CANAVIEIRAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290640, 'CANDEAL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290650, 'CANDEIAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290660, 'CANDIBA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290670, 'CANDIDO SALES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290680, 'CANSANCAO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290682, 'CANUDOS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290685, 'CAPELA DO ALTO ALEGRE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290687, 'CAPIM GROSSO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290689, 'CARAIBAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290690, 'CARAVELAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290700, 'CARDEAL DA SILVA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290710, 'CARINHANHA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290720, 'CASA NOVA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290730, 'CASTRO ALVES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290740, 'CATOLANDIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290750, 'CATU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290755, 'CATURAMA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290760, 'CENTRAL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290770, 'CHORROCHO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290780, 'CICERO DANTAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290790, 'CIPO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290800, 'COARACI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290810, 'COCOS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290820, 'CONCEICAO DA FEIRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290830, 'CONCEICAO DO ALMEIDA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290840, 'CONCEICAO DO COITE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290850, 'CONCEICAO DO JACUIPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290860, 'CONDE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290870, 'CONDEUBA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290880, 'CONTENDAS DO SINCORA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290890, 'CORACAO DE MARIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290900, 'CORDEIROS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290910, 'CORIBE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290920, 'CORONEL JOAO SA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290930, 'CORRENTINA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290940, 'COTEGIPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290950, 'CRAVOLANDIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290960, 'CRISOPOLIS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290970, 'CRISTOPOLIS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290980, 'CRUZ DAS ALMAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (290990, 'CURACA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291000, 'DARIO MEIRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291005, 'DIAS D AVILA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291010, 'DOM BASILIO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291020, 'DOM MACEDO COSTA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291030, 'ELISIO MEDRADO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291040, 'ENCRUZILHADA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291050, 'ENTRE RIOS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291060, 'ESPLANADA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291070, 'EUCLIDES DA CUNHA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291072, 'EUNAPOLIS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291075, 'FATIMA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291077, 'FEIRA DA MATA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291080, 'FEIRA DE SANTANA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291085, 'FILADELFIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291090, 'FIRMINO ALVES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291100, 'FLORESTA AZUL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291110, 'FORMOSA DO RIO PRETO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291120, 'GANDU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291125, 'GAVIAO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291130, 'GENTIO DO OURO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291140, 'GLORIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291150, 'GONGOGI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291160, 'GOVERNADOR MANGABEIRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291165, 'GUAJERU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291170, 'GUANAMBI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291180, 'GUARATINGA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291185, 'HELIOPOLIS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291190, 'IACU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291200, 'IBIASSUCE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291210, 'IBICARAI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291220, 'IBICOARA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291230, 'IBICUI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291240, 'IBIPEBA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291250, 'IBIPITANGA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291260, 'IBIQUERA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291270, 'IBIRAPITANGA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291280, 'IBIRAPUA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291290, 'IBIRATAIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291300, 'IBITIARA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291310, 'IBITITA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291320, 'IBOTIRAMA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291330, 'ICHU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291340, 'IGAPORA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291345, 'IGRAPIUNA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291350, 'IGUAI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291360, 'ILHEUS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291370, 'INHAMBUPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291380, 'IPECAETA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291390, 'IPIAU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291400, 'IPIRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291410, 'IPUPIARA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291420, 'IRAJUBA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291430, 'IRAMAIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291440, 'IRAQUARA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291450, 'IRARA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291460, 'IRECE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291465, 'ITABELA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291470, 'ITABERABA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291480, 'ITABUNA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291490, 'ITACARE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291500, 'ITAETE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291510, 'ITAGI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291520, 'ITAGIBA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291530, 'ITAGIMIRIM', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291535, 'ITAGUACU DA BAHIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291540, 'ITAJU DO COLONIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291550, 'ITAJUIPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291560, 'ITAMARAJU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291570, 'ITAMARI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291580, 'ITAMBE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291590, 'ITANAGRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291600, 'ITANHEM', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291610, 'ITAPARICA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291620, 'ITAPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291630, 'ITAPEBI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291640, 'ITAPETINGA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291650, 'ITAPICURU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291660, 'ITAPITANGA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291670, 'ITAQUARA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291680, 'ITARANTIM', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291685, 'ITATIM', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291690, 'ITIRUCU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291700, 'ITIUBA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291710, 'ITORORO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291720, 'ITUACU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291730, 'ITUBERA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291733, 'IUIU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291735, 'JABORANDI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291740, 'JACARACI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291750, 'JACOBINA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291760, 'JAGUAQUARA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291770, 'JAGUARARI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291780, 'JAGUARIPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291790, 'JANDAIRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291800, 'JEQUIE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291810, 'JEREMOABO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291820, 'JIQUIRICA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291830, 'JITAUNA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291835, 'JOAO DOURADO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291840, 'JUAZEIRO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291845, 'JUCURUCU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291850, 'JUSSARA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291855, 'JUSSARI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291860, 'JUSSIAPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291870, 'LAFAIETE COUTINHO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291875, 'LAGOA REAL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291880, 'LAJE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291890, 'LAJEDAO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291900, 'LAJEDINHO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291905, 'LAJEDO DO TABOCAL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291910, 'LAMARAO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291915, 'LAPAO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291920, 'LAURO DE FREITAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291930, 'LENCOIS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291940, 'LICINIO DE ALMEIDA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291950, 'LIVRAMENTO DE NOSSA SENHORA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291955, 'LUIS EDUARDO MAGALHAES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291960, 'MACAJUBA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291970, 'MACARANI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291980, 'MACAUBAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291990, 'MACURURE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291992, 'MADRE DE DEUS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (291995, 'MAETINGA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292000, 'MAIQUINIQUE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292010, 'MAIRI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292020, 'MALHADA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292030, 'MALHADA DE PEDRAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292040, 'MANOEL VITORINO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292045, 'MANSIDAO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292050, 'MARACAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292060, 'MARAGOGIPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292070, 'MARAU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292080, 'MARCIONILIO SOUZA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292090, 'MASCOTE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292100, 'MATA DE SAO JOAO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292105, 'MATINA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292110, 'MEDEIROS NETO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292120, 'MIGUEL CALMON', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292130, 'MILAGRES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292140, 'MIRANGABA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292145, 'MIRANTE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292150, 'MONTE SANTO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292160, 'MORPARA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292170, 'MORRO DO CHAPEU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292180, 'MORTUGABA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292190, 'MUCUGE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292200, 'MUCURI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292205, 'MULUNGU DO MORRO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292210, 'MUNDO NOVO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292220, 'MUNIZ FERREIRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292225, 'MUQUEM DE SAO FRANCISCO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292230, 'MURITIBA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292240, 'MUTUIPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292250, 'NAZARE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292260, 'NILO PECANHA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292265, 'NORDESTINA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292270, 'NOVA CANAA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292273, 'NOVA FATIMA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292275, 'NOVA IBIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292280, 'NOVA ITARANA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292285, 'NOVA REDENCAO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292290, 'NOVA SOURE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292300, 'NOVA VICOSA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292303, 'NOVO HORIZONTE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292305, 'NOVO TRIUNFO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292310, 'OLINDINA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292320, 'OLIVEIRA DOS BREJINHOS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292330, 'OURICANGAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292335, 'OUROLANDIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292340, 'PALMAS DE MONTE ALTO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292350, 'PALMEIRAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292360, 'PARAMIRIM', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292370, 'PARATINGA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292380, 'PARIPIRANGA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292390, 'PAU BRASIL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292400, 'PAULO AFONSO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292405, 'PE DE SERRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292410, 'PEDRAO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292420, 'PEDRO ALEXANDRE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292430, 'PIATA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292440, 'PILAO ARCADO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292450, 'PINDAI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292460, 'PINDOBACU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292465, 'PINTADAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292467, 'PIRAI DO NORTE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292470, 'PIRIPA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292480, 'PIRITIBA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292490, 'PLANALTINO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292500, 'PLANALTO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292510, 'POCOES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292520, 'POJUCA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292525, 'PONTO NOVO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292530, 'PORTO SEGURO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292540, 'POTIRAGUA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292550, 'PRADO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292560, 'PRESIDENTE DUTRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292570, 'PRESIDENTE JANIO QUADROS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292575, 'PRESIDENTE TANCREDO NEVES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292580, 'QUEIMADAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292590, 'QUIJINGUE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292593, 'QUIXABEIRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292595, 'RAFAEL JAMBEIRO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292600, 'REMANSO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292610, 'RETIROLANDIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292620, 'RIACHAO DAS NEVES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292630, 'RIACHAO DO JACUIPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292640, 'RIACHO DE SANTANA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292650, 'RIBEIRA DO AMPARO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292660, 'RIBEIRA DO POMBAL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292665, 'RIBEIRAO DO LARGO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292670, 'RIO DE CONTAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292680, 'RIO DO ANTONIO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292690, 'RIO DO PIRES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292700, 'RIO REAL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292710, 'RODELAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292720, 'RUY BARBOSA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292730, 'SALINAS DA MARGARIDA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292740, 'SALVADOR', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292750, 'SANTA BARBARA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292760, 'SANTA BRIGIDA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292770, 'SANTA CRUZ CABRALIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292780, 'SANTA CRUZ DA VITORIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292790, 'SANTA INES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292800, 'SANTALUZ', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292805, 'SANTA LUZIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292810, 'SANTA MARIA DA VITORIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292820, 'SANTANA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292830, 'SANTANOPOLIS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292840, 'SANTA RITA DE CASSIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292850, 'SANTA TERESINHA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292860, 'SANTO AMARO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292870, 'SANTO ANTONIO DE JESUS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292880, 'SANTO ESTEVAO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292890, 'SAO DESIDERIO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292895, 'SAO DOMINGOS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292900, 'SAO FELIX', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292905, 'SAO FELIX DO CORIBE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292910, 'SAO FELIPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292920, 'SAO FRANCISCO DO CONDE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292925, 'SAO GABRIEL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292930, 'SAO GONCALO DOS CAMPOS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292935, 'SAO JOSE DA VITORIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292937, 'SAO JOSE DO JACUIPE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292940, 'SAO MIGUEL DAS MATAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292950, 'SAO SEBASTIAO DO PASSE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292960, 'SAPEACU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292970, 'SATIRO DIAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292975, 'SAUBARA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292980, 'SAUDE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (292990, 'SEABRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293000, 'SEBASTIAO LARANJEIRAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293010, 'SENHOR DO BONFIM', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293015, 'SERRA DO RAMALHO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293020, 'SENTO SE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293030, 'SERRA DOURADA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293040, 'SERRA PRETA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293050, 'SERRINHA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293060, 'SERROLANDIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293070, 'SIMOES FILHO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293075, 'SITIO DO MATO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293076, 'SITIO DO QUINTO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293077, 'SOBRADINHO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293080, 'SOUTO SOARES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293090, 'TABOCAS DO BREJO VELHO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293100, 'TANHACU', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293105, 'TANQUE NOVO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293110, 'TANQUINHO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293120, 'TAPEROA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293130, 'TAPIRAMUTA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293135, 'TEIXEIRA DE FREITAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293140, 'TEODORO SAMPAIO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293150, 'TEOFILANDIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293160, 'TEOLANDIA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293170, 'TERRA NOVA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293180, 'TREMEDAL', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293190, 'TUCANO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293200, 'UAUA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293210, 'UBAIRA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293220, 'UBAITABA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293230, 'UBATA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293240, 'UIBAI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293245, 'UMBURANAS', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293250, 'UNA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293260, 'URANDI', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293270, 'URUCUCA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293280, 'UTINGA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293290, 'VALENCA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293300, 'VALENTE', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293305, 'VARZEA DA ROCA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293310, 'VARZEA DO POCO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293315, 'VARZEA NOVA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293317, 'VARZEDO', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293320, 'VERA CRUZ', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293325, 'VEREDA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293330, 'VITORIA DA CONQUISTA', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293340, 'WAGNER', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293345, 'WANDERLEY', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293350, 'WENCESLAU GUIMARAES', 'BA'); ");
        script.append(defaultInsertCidade).append(" VALUES  (293360, 'XIQUE-XIQUE', 'BA'); ");
                
        return script.toString();
    }
    
    /**
     * Método obtém script de insert da tabela USUARIO com usuário do sistema.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getInsertIntoUsuario() {

        StringBuilder script = new StringBuilder();
        String defaultInsertUsuario = "INSERT INTO " + UsuarioVO.TABLE + " (" + UsuarioVO.TX_LOGIN + ", " + UsuarioVO.TX_SENHA + ", " + UsuarioVO.TIPO_USUARIO + ", " + UsuarioVO.TX_NOME + ")";
        
        UsuarioVO usuario = this.obterUsuarioSistema();
        
        script.append(defaultInsertUsuario).append(" VALUES ('" + usuario.getLogin() + "', '" + usuario.getSenha() + "', " + usuario.getTipoUsuario() + ", '" + usuario.getNome() + "'); ");

        return script.toString();
    }
    
    /**
     * Método obtém script de drop da tabela.
     * 
     * @param table - Nome da tabela
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    @SuppressWarnings("unused")
    private String getDropTable(String table) {

        return "DROP TABLE IF EXISTS " + table;
    }
}
