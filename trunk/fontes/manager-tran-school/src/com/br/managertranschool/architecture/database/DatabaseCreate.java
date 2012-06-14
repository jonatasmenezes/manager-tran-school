package com.br.managertranschool.architecture.database;

import android.content.Context;

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
    private String[][] getScriptInsert() {

        return new String[][] { this.getInsertIntoUsuario(), this.getInsertIntoEstado(), this.getInsertIntoCidade(), this.getInsertIntoLocalidade() };
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
     * @return Array com scripts
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String[] getInsertIntoEstado() {

        String defaultInsertEstado = "INSERT INTO " + EstadoVO.TABLE + " (" + EstadoVO.ID_ESTADO + ", " + EstadoVO.TX_DESCRICAO + ")";
        
        String[] scriptsInsert = 
        {
            defaultInsertEstado + " VALUES ('AC', 'Acre'); ",
            defaultInsertEstado + " VALUES ('AL', 'Alagoas'); ",
            defaultInsertEstado + " VALUES ('AP', 'Amapá'); ",
            defaultInsertEstado + " VALUES ('AM', 'Amazonas'); ",
            defaultInsertEstado + " VALUES ('BA', 'Bahia'); ",
            defaultInsertEstado + " VALUES ('CE', 'Ceará'); ",
            defaultInsertEstado + " VALUES ('DF', 'Distrito Federal'); ",
            defaultInsertEstado + " VALUES ('ES', 'Espírito Santo'); ",
            defaultInsertEstado + " VALUES ('GO', 'Goiás'); ",
            defaultInsertEstado + " VALUES ('MA', 'Maranhão'); ",
            defaultInsertEstado + " VALUES ('MT', 'Mato Grosso'); ",
            defaultInsertEstado + " VALUES ('MS', 'Mato Grosso do Sul'); ",
            defaultInsertEstado + " VALUES ('MG', 'Minas Gerais'); ",
            defaultInsertEstado + " VALUES ('PE', 'Pernambuco'); ",
            defaultInsertEstado + " VALUES ('PI', 'Piauí'); ",
            defaultInsertEstado + " VALUES ('RJ', 'Rio de Janeiro'); ",
            defaultInsertEstado + " VALUES ('RN', 'Rio Grande do Norte'); ",
            defaultInsertEstado + " VALUES ('RS', 'Rio Grande do Sul'); ",
            defaultInsertEstado + " VALUES ('RO', 'Rondônia'); ",
            defaultInsertEstado + " VALUES ('RR', 'Roraima'); ",
            defaultInsertEstado + " VALUES ('SC', 'Santa Catarina'); ",
            defaultInsertEstado + " VALUES ('PR', 'Paraná'); ",
            defaultInsertEstado + " VALUES ('PB', 'Paraíba'); ",
            defaultInsertEstado + " VALUES ('PA', 'Pará'); ",
            defaultInsertEstado + " VALUES ('SP', 'São Paulo'); ",
            defaultInsertEstado + " VALUES ('SE', 'Sergipe'); ",
            defaultInsertEstado + " VALUES ('TO', 'Tocantins'); ",
        };
        
        
        return scriptsInsert;
    }
    
    /**
     * Método obtém script de insert da tabela CIDADE contendo cidades do Estado da Bahia.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String[] getInsertIntoCidade() {
        String defaultInsertCidade = "INSERT INTO " + CidadeVO.TABLE + " (" + CidadeVO.ID_CIDADE + ", " + CidadeVO.TX_DESCRICAO + ", " + CidadeVO.ESTADO_ID + ")";
        
        String[] scriptsInsert = 
        {        
            defaultInsertCidade + " VALUES  (290010, 'ABAIRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290020, 'ABARE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290030, 'ACAJUTIBA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290035, 'ADUSTINA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290040, 'AGUA FRIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290050, 'ERICO CARDOSO', 'BA'); ",
            defaultInsertCidade + " VALUES  (290060, 'AIQUARA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290070, 'ALAGOINHAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290080, 'ALCOBACA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290090, 'ALMADINA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290100, 'AMARGOSA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290110, 'AMELIA RODRIGUES', 'BA'); ",
            defaultInsertCidade + " VALUES  (290115, 'AMERICA DOURADA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290120, 'ANAGE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290130, 'ANDARAI', 'BA'); ",
            defaultInsertCidade + " VALUES  (290135, 'ANDORINHA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290140, 'ANGICAL', 'BA'); ",
            defaultInsertCidade + " VALUES  (290150, 'ANGUERA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290160, 'ANTAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290170, 'ANTONIO CARDOSO', 'BA'); ",
            defaultInsertCidade + " VALUES  (290180, 'ANTONIO GONCALVES', 'BA'); ",
            defaultInsertCidade + " VALUES  (290190, 'APORA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290195, 'APUAREMA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290200, 'ARACATU', 'BA'); ",
            defaultInsertCidade + " VALUES  (290205, 'ARACAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290210, 'ARACI', 'BA'); ",
            defaultInsertCidade + " VALUES  (290220, 'ARAMARI', 'BA'); ",
            defaultInsertCidade + " VALUES  (290225, 'ARATACA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290230, 'ARATUIPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290240, 'AURELINO LEAL', 'BA'); ",
            defaultInsertCidade + " VALUES  (290250, 'BAIANOPOLIS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290260, 'BAIXA GRANDE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290265, 'BANZAE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290270, 'BARRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290280, 'BARRA DA ESTIVA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290290, 'BARRA DO CHOCA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290300, 'BARRA DO MENDES', 'BA'); ",
            defaultInsertCidade + " VALUES  (290310, 'BARRA DO ROCHA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290320, 'BARREIRAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290323, 'BARRO ALTO', 'BA'); ",
            defaultInsertCidade + " VALUES  (290327, 'BARROCAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290330, 'BARRO PRETO', 'BA'); ",
            defaultInsertCidade + " VALUES  (290340, 'BELMONTE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290350, 'BELO CAMPO', 'BA'); ",
            defaultInsertCidade + " VALUES  (290360, 'BIRITINGA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290370, 'BOA NOVA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290380, 'BOA VISTA DO TUPIM', 'BA'); ",
            defaultInsertCidade + " VALUES  (290390, 'BOM JESUS DA LAPA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290395, 'BOM JESUS DA SERRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290400, 'BONINAL', 'BA'); ",
            defaultInsertCidade + " VALUES  (290405, 'BONITO', 'BA'); ",
            defaultInsertCidade + " VALUES  (290410, 'BOQUIRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290420, 'BOTUPORA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290430, 'BREJOES', 'BA'); ",
            defaultInsertCidade + " VALUES  (290440, 'BREJOLANDIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290450, 'BROTAS DE MACAUBAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290460, 'BRUMADO', 'BA'); ",
            defaultInsertCidade + " VALUES  (290470, 'BUERAREMA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290475, 'BURITIRAMA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290480, 'CAATIBA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290485, 'CABACEIRAS DO PARAGUACU', 'BA'); ",
            defaultInsertCidade + " VALUES  (290490, 'CACHOEIRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290500, 'CACULE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290510, 'CAEM', 'BA'); ",
            defaultInsertCidade + " VALUES  (290515, 'CAETANOS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290520, 'CAETITE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290530, 'CAFARNAUM', 'BA'); ",
            defaultInsertCidade + " VALUES  (290540, 'CAIRU', 'BA'); ",
            defaultInsertCidade + " VALUES  (290550, 'CALDEIRAO GRANDE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290560, 'CAMACAN', 'BA'); ",
            defaultInsertCidade + " VALUES  (290570, 'CAMACARI', 'BA'); ",
            defaultInsertCidade + " VALUES  (290580, 'CAMAMU', 'BA'); ",
            defaultInsertCidade + " VALUES  (290590, 'CAMPO ALEGRE DE LOURDES', 'BA'); ",
            defaultInsertCidade + " VALUES  (290600, 'CAMPO FORMOSO', 'BA'); ",
            defaultInsertCidade + " VALUES  (290610, 'CANAPOLIS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290620, 'CANARANA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290630, 'CANAVIEIRAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290640, 'CANDEAL', 'BA'); ",
            defaultInsertCidade + " VALUES  (290650, 'CANDEIAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290660, 'CANDIBA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290670, 'CANDIDO SALES', 'BA'); ",
            defaultInsertCidade + " VALUES  (290680, 'CANSANCAO', 'BA'); ",
            defaultInsertCidade + " VALUES  (290682, 'CANUDOS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290685, 'CAPELA DO ALTO ALEGRE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290687, 'CAPIM GROSSO', 'BA'); ",
            defaultInsertCidade + " VALUES  (290689, 'CARAIBAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290690, 'CARAVELAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290700, 'CARDEAL DA SILVA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290710, 'CARINHANHA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290720, 'CASA NOVA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290730, 'CASTRO ALVES', 'BA'); ",
            defaultInsertCidade + " VALUES  (290740, 'CATOLANDIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290750, 'CATU', 'BA'); ",
            defaultInsertCidade + " VALUES  (290755, 'CATURAMA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290760, 'CENTRAL', 'BA'); ",
            defaultInsertCidade + " VALUES  (290770, 'CHORROCHO', 'BA'); ",
            defaultInsertCidade + " VALUES  (290780, 'CICERO DANTAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290790, 'CIPO', 'BA'); ",
            defaultInsertCidade + " VALUES  (290800, 'COARACI', 'BA'); ",
            defaultInsertCidade + " VALUES  (290810, 'COCOS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290820, 'CONCEICAO DA FEIRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290830, 'CONCEICAO DO ALMEIDA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290840, 'CONCEICAO DO COITE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290850, 'CONCEICAO DO JACUIPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290860, 'CONDE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290870, 'CONDEUBA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290880, 'CONTENDAS DO SINCORA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290890, 'CORACAO DE MARIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290900, 'CORDEIROS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290910, 'CORIBE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290920, 'CORONEL JOAO SA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290930, 'CORRENTINA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290940, 'COTEGIPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (290950, 'CRAVOLANDIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (290960, 'CRISOPOLIS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290970, 'CRISTOPOLIS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290980, 'CRUZ DAS ALMAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (290990, 'CURACA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291000, 'DARIO MEIRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291005, 'DIAS D AVILA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291010, 'DOM BASILIO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291020, 'DOM MACEDO COSTA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291030, 'ELISIO MEDRADO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291040, 'ENCRUZILHADA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291050, 'ENTRE RIOS', 'BA'); ",
            defaultInsertCidade + " VALUES  (291060, 'ESPLANADA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291070, 'EUCLIDES DA CUNHA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291072, 'EUNAPOLIS', 'BA'); ",
            defaultInsertCidade + " VALUES  (291075, 'FATIMA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291077, 'FEIRA DA MATA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291080, 'FEIRA DE SANTANA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291085, 'FILADELFIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291090, 'FIRMINO ALVES', 'BA'); ",
            defaultInsertCidade + " VALUES  (291100, 'FLORESTA AZUL', 'BA'); ",
            defaultInsertCidade + " VALUES  (291110, 'FORMOSA DO RIO PRETO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291120, 'GANDU', 'BA'); ",
            defaultInsertCidade + " VALUES  (291125, 'GAVIAO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291130, 'GENTIO DO OURO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291140, 'GLORIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291150, 'GONGOGI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291160, 'GOVERNADOR MANGABEIRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291165, 'GUAJERU', 'BA'); ",
            defaultInsertCidade + " VALUES  (291170, 'GUANAMBI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291180, 'GUARATINGA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291185, 'HELIOPOLIS', 'BA'); ",
            defaultInsertCidade + " VALUES  (291190, 'IACU', 'BA'); ",
            defaultInsertCidade + " VALUES  (291200, 'IBIASSUCE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291210, 'IBICARAI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291220, 'IBICOARA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291230, 'IBICUI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291240, 'IBIPEBA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291250, 'IBIPITANGA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291260, 'IBIQUERA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291270, 'IBIRAPITANGA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291280, 'IBIRAPUA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291290, 'IBIRATAIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291300, 'IBITIARA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291310, 'IBITITA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291320, 'IBOTIRAMA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291330, 'ICHU', 'BA'); ",
            defaultInsertCidade + " VALUES  (291340, 'IGAPORA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291345, 'IGRAPIUNA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291350, 'IGUAI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291360, 'ILHEUS', 'BA'); ",
            defaultInsertCidade + " VALUES  (291370, 'INHAMBUPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291380, 'IPECAETA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291390, 'IPIAU', 'BA'); ",
            defaultInsertCidade + " VALUES  (291400, 'IPIRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291410, 'IPUPIARA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291420, 'IRAJUBA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291430, 'IRAMAIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291440, 'IRAQUARA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291450, 'IRARA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291460, 'IRECE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291465, 'ITABELA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291470, 'ITABERABA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291480, 'ITABUNA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291490, 'ITACARE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291500, 'ITAETE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291510, 'ITAGI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291520, 'ITAGIBA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291530, 'ITAGIMIRIM', 'BA'); ",
            defaultInsertCidade + " VALUES  (291535, 'ITAGUACU DA BAHIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291540, 'ITAJU DO COLONIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291550, 'ITAJUIPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291560, 'ITAMARAJU', 'BA'); ",
            defaultInsertCidade + " VALUES  (291570, 'ITAMARI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291580, 'ITAMBE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291590, 'ITANAGRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291600, 'ITANHEM', 'BA'); ",
            defaultInsertCidade + " VALUES  (291610, 'ITAPARICA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291620, 'ITAPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291630, 'ITAPEBI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291640, 'ITAPETINGA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291650, 'ITAPICURU', 'BA'); ",
            defaultInsertCidade + " VALUES  (291660, 'ITAPITANGA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291670, 'ITAQUARA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291680, 'ITARANTIM', 'BA'); ",
            defaultInsertCidade + " VALUES  (291685, 'ITATIM', 'BA'); ",
            defaultInsertCidade + " VALUES  (291690, 'ITIRUCU', 'BA'); ",
            defaultInsertCidade + " VALUES  (291700, 'ITIUBA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291710, 'ITORORO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291720, 'ITUACU', 'BA'); ",
            defaultInsertCidade + " VALUES  (291730, 'ITUBERA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291733, 'IUIU', 'BA'); ",
            defaultInsertCidade + " VALUES  (291735, 'JABORANDI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291740, 'JACARACI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291750, 'JACOBINA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291760, 'JAGUAQUARA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291770, 'JAGUARARI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291780, 'JAGUARIPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291790, 'JANDAIRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291800, 'JEQUIE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291810, 'JEREMOABO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291820, 'JIQUIRICA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291830, 'JITAUNA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291835, 'JOAO DOURADO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291840, 'JUAZEIRO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291845, 'JUCURUCU', 'BA'); ",
            defaultInsertCidade + " VALUES  (291850, 'JUSSARA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291855, 'JUSSARI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291860, 'JUSSIAPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291870, 'LAFAIETE COUTINHO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291875, 'LAGOA REAL', 'BA'); ",
            defaultInsertCidade + " VALUES  (291880, 'LAJE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291890, 'LAJEDAO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291900, 'LAJEDINHO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291905, 'LAJEDO DO TABOCAL', 'BA'); ",
            defaultInsertCidade + " VALUES  (291910, 'LAMARAO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291915, 'LAPAO', 'BA'); ",
            defaultInsertCidade + " VALUES  (291920, 'LAURO DE FREITAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (291930, 'LENCOIS', 'BA'); ",
            defaultInsertCidade + " VALUES  (291940, 'LICINIO DE ALMEIDA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291950, 'LIVRAMENTO DE NOSSA SENHORA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291955, 'LUIS EDUARDO MAGALHAES', 'BA'); ",
            defaultInsertCidade + " VALUES  (291960, 'MACAJUBA', 'BA'); ",
            defaultInsertCidade + " VALUES  (291970, 'MACARANI', 'BA'); ",
            defaultInsertCidade + " VALUES  (291980, 'MACAUBAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (291990, 'MACURURE', 'BA'); ",
            defaultInsertCidade + " VALUES  (291992, 'MADRE DE DEUS', 'BA'); ",
            defaultInsertCidade + " VALUES  (291995, 'MAETINGA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292000, 'MAIQUINIQUE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292010, 'MAIRI', 'BA'); ",
            defaultInsertCidade + " VALUES  (292020, 'MALHADA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292030, 'MALHADA DE PEDRAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292040, 'MANOEL VITORINO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292045, 'MANSIDAO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292050, 'MARACAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292060, 'MARAGOGIPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292070, 'MARAU', 'BA'); ",
            defaultInsertCidade + " VALUES  (292080, 'MARCIONILIO SOUZA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292090, 'MASCOTE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292100, 'MATA DE SAO JOAO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292105, 'MATINA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292110, 'MEDEIROS NETO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292120, 'MIGUEL CALMON', 'BA'); ",
            defaultInsertCidade + " VALUES  (292130, 'MILAGRES', 'BA'); ",
            defaultInsertCidade + " VALUES  (292140, 'MIRANGABA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292145, 'MIRANTE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292150, 'MONTE SANTO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292160, 'MORPARA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292170, 'MORRO DO CHAPEU', 'BA'); ",
            defaultInsertCidade + " VALUES  (292180, 'MORTUGABA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292190, 'MUCUGE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292200, 'MUCURI', 'BA'); ",
            defaultInsertCidade + " VALUES  (292205, 'MULUNGU DO MORRO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292210, 'MUNDO NOVO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292220, 'MUNIZ FERREIRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292225, 'MUQUEM DE SAO FRANCISCO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292230, 'MURITIBA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292240, 'MUTUIPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292250, 'NAZARE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292260, 'NILO PECANHA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292265, 'NORDESTINA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292270, 'NOVA CANAA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292273, 'NOVA FATIMA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292275, 'NOVA IBIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292280, 'NOVA ITARANA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292285, 'NOVA REDENCAO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292290, 'NOVA SOURE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292300, 'NOVA VICOSA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292303, 'NOVO HORIZONTE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292305, 'NOVO TRIUNFO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292310, 'OLINDINA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292320, 'OLIVEIRA DOS BREJINHOS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292330, 'OURICANGAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292335, 'OUROLANDIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292340, 'PALMAS DE MONTE ALTO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292350, 'PALMEIRAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292360, 'PARAMIRIM', 'BA'); ",
            defaultInsertCidade + " VALUES  (292370, 'PARATINGA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292380, 'PARIPIRANGA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292390, 'PAU BRASIL', 'BA'); ",
            defaultInsertCidade + " VALUES  (292400, 'PAULO AFONSO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292405, 'PE DE SERRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292410, 'PEDRAO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292420, 'PEDRO ALEXANDRE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292430, 'PIATA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292440, 'PILAO ARCADO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292450, 'PINDAI', 'BA'); ",
            defaultInsertCidade + " VALUES  (292460, 'PINDOBACU', 'BA'); ",
            defaultInsertCidade + " VALUES  (292465, 'PINTADAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292467, 'PIRAI DO NORTE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292470, 'PIRIPA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292480, 'PIRITIBA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292490, 'PLANALTINO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292500, 'PLANALTO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292510, 'POCOES', 'BA'); ",
            defaultInsertCidade + " VALUES  (292520, 'POJUCA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292525, 'PONTO NOVO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292530, 'PORTO SEGURO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292540, 'POTIRAGUA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292550, 'PRADO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292560, 'PRESIDENTE DUTRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292570, 'PRESIDENTE JANIO QUADROS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292575, 'PRESIDENTE TANCREDO NEVES', 'BA'); ",
            defaultInsertCidade + " VALUES  (292580, 'QUEIMADAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292590, 'QUIJINGUE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292593, 'QUIXABEIRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292595, 'RAFAEL JAMBEIRO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292600, 'REMANSO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292610, 'RETIROLANDIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292620, 'RIACHAO DAS NEVES', 'BA'); ",
            defaultInsertCidade + " VALUES  (292630, 'RIACHAO DO JACUIPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292640, 'RIACHO DE SANTANA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292650, 'RIBEIRA DO AMPARO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292660, 'RIBEIRA DO POMBAL', 'BA'); ",
            defaultInsertCidade + " VALUES  (292665, 'RIBEIRAO DO LARGO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292670, 'RIO DE CONTAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292680, 'RIO DO ANTONIO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292690, 'RIO DO PIRES', 'BA'); ",
            defaultInsertCidade + " VALUES  (292700, 'RIO REAL', 'BA'); ",
            defaultInsertCidade + " VALUES  (292710, 'RODELAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292720, 'RUY BARBOSA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292730, 'SALINAS DA MARGARIDA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292740, 'SALVADOR', 'BA'); ",
            defaultInsertCidade + " VALUES  (292750, 'SANTA BARBARA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292760, 'SANTA BRIGIDA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292770, 'SANTA CRUZ CABRALIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292780, 'SANTA CRUZ DA VITORIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292790, 'SANTA INES', 'BA'); ",
            defaultInsertCidade + " VALUES  (292800, 'SANTALUZ', 'BA'); ",
            defaultInsertCidade + " VALUES  (292805, 'SANTA LUZIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292810, 'SANTA MARIA DA VITORIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292820, 'SANTANA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292830, 'SANTANOPOLIS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292840, 'SANTA RITA DE CASSIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292850, 'SANTA TERESINHA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292860, 'SANTO AMARO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292870, 'SANTO ANTONIO DE JESUS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292880, 'SANTO ESTEVAO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292890, 'SAO DESIDERIO', 'BA'); ",
            defaultInsertCidade + " VALUES  (292895, 'SAO DOMINGOS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292900, 'SAO FELIX', 'BA'); ",
            defaultInsertCidade + " VALUES  (292905, 'SAO FELIX DO CORIBE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292910, 'SAO FELIPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292920, 'SAO FRANCISCO DO CONDE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292925, 'SAO GABRIEL', 'BA'); ",
            defaultInsertCidade + " VALUES  (292930, 'SAO GONCALO DOS CAMPOS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292935, 'SAO JOSE DA VITORIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292937, 'SAO JOSE DO JACUIPE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292940, 'SAO MIGUEL DAS MATAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292950, 'SAO SEBASTIAO DO PASSE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292960, 'SAPEACU', 'BA'); ",
            defaultInsertCidade + " VALUES  (292970, 'SATIRO DIAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (292975, 'SAUBARA', 'BA'); ",
            defaultInsertCidade + " VALUES  (292980, 'SAUDE', 'BA'); ",
            defaultInsertCidade + " VALUES  (292990, 'SEABRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293000, 'SEBASTIAO LARANJEIRAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (293010, 'SENHOR DO BONFIM', 'BA'); ",
            defaultInsertCidade + " VALUES  (293015, 'SERRA DO RAMALHO', 'BA'); ",
            defaultInsertCidade + " VALUES  (293020, 'SENTO SE', 'BA'); ",
            defaultInsertCidade + " VALUES  (293030, 'SERRA DOURADA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293040, 'SERRA PRETA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293050, 'SERRINHA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293060, 'SERROLANDIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293070, 'SIMOES FILHO', 'BA'); ",
            defaultInsertCidade + " VALUES  (293075, 'SITIO DO MATO', 'BA'); ",
            defaultInsertCidade + " VALUES  (293076, 'SITIO DO QUINTO', 'BA'); ",
            defaultInsertCidade + " VALUES  (293077, 'SOBRADINHO', 'BA'); ",
            defaultInsertCidade + " VALUES  (293080, 'SOUTO SOARES', 'BA'); ",
            defaultInsertCidade + " VALUES  (293090, 'TABOCAS DO BREJO VELHO', 'BA'); ",
            defaultInsertCidade + " VALUES  (293100, 'TANHACU', 'BA'); ",
            defaultInsertCidade + " VALUES  (293105, 'TANQUE NOVO', 'BA'); ",
            defaultInsertCidade + " VALUES  (293110, 'TANQUINHO', 'BA'); ",
            defaultInsertCidade + " VALUES  (293120, 'TAPEROA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293130, 'TAPIRAMUTA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293135, 'TEIXEIRA DE FREITAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (293140, 'TEODORO SAMPAIO', 'BA'); ",
            defaultInsertCidade + " VALUES  (293150, 'TEOFILANDIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293160, 'TEOLANDIA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293170, 'TERRA NOVA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293180, 'TREMEDAL', 'BA'); ",
            defaultInsertCidade + " VALUES  (293190, 'TUCANO', 'BA'); ",
            defaultInsertCidade + " VALUES  (293200, 'UAUA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293210, 'UBAIRA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293220, 'UBAITABA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293230, 'UBATA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293240, 'UIBAI', 'BA'); ",
            defaultInsertCidade + " VALUES  (293245, 'UMBURANAS', 'BA'); ",
            defaultInsertCidade + " VALUES  (293250, 'UNA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293260, 'URANDI', 'BA'); ",
            defaultInsertCidade + " VALUES  (293270, 'URUCUCA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293280, 'UTINGA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293290, 'VALENCA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293300, 'VALENTE', 'BA'); ",
            defaultInsertCidade + " VALUES  (293305, 'VARZEA DA ROCA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293310, 'VARZEA DO POCO', 'BA'); ",
            defaultInsertCidade + " VALUES  (293315, 'VARZEA NOVA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293317, 'VARZEDO', 'BA'); ",
            defaultInsertCidade + " VALUES  (293320, 'VERA CRUZ', 'BA'); ",
            defaultInsertCidade + " VALUES  (293325, 'VEREDA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293330, 'VITORIA DA CONQUISTA', 'BA'); ",
            defaultInsertCidade + " VALUES  (293340, 'WAGNER', 'BA'); ",
            defaultInsertCidade + " VALUES  (293345, 'WANDERLEY', 'BA'); ",
            defaultInsertCidade + " VALUES  (293350, 'WENCESLAU GUIMARAES', 'BA'); ",
            defaultInsertCidade + " VALUES  (293360, 'XIQUE-XIQUE', 'BA'); ",
        };        
        
        return scriptsInsert;
    }
    
    /**
     * Método obtém script de insert da tabela USUARIO com usuário do sistema.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String[] getInsertIntoUsuario() {

        String defaultInsertUsuario = "INSERT INTO " + UsuarioVO.TABLE + " (" + UsuarioVO.TX_LOGIN + ", " + UsuarioVO.TX_SENHA + ", " + UsuarioVO.TIPO_USUARIO + ", " + UsuarioVO.TX_NOME + ")";
        
        UsuarioVO usuario = this.obterUsuarioSistema();
        
        String[] scriptsInsert = 
        { 
            defaultInsertUsuario + " VALUES ('" + usuario.getLogin() + "', '" + usuario.getSenha() + "', " + usuario.getTipoUsuario() + ", '" + usuario.getNome() + "'); "
        };

        return scriptsInsert;
    }
    
    /**
     * Método obtém script de insert da tabela LOCALIDADE para exemplo de uso.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String[] getInsertIntoLocalidade() {

        String defaultInsertLocalidade = "INSERT INTO " + LocalidadeVO.TABLE + " (" + LocalidadeVO.NR_LATITUDE + ", " + LocalidadeVO.NR_LONGITUDE + ", " + LocalidadeVO.TX_DESCRICAO + ")";
        
        String[] scriptsInsert = 
        { 
            defaultInsertLocalidade + " VALUES (-12.998578, -38.448458, 'Fernando Menezes de Góes - Costa Azul'); ",
            defaultInsertLocalidade + " VALUES (-12.981684, -38.463907, 'Shopping Iguatemi'); ",
            defaultInsertLocalidade + " VALUES (-13.007819, -38.518324, 'Av. Oceânica - Barra'); ",
        };

        return scriptsInsert;
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
