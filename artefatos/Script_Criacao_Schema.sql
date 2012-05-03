SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `manager-tran-school` ;
CREATE SCHEMA IF NOT EXISTS `manager-tran-school` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `manager-tran-school` ;

-- -----------------------------------------------------
-- Table `manager-tran-school`.`USUARIO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `manager-tran-school`.`USUARIO` ;

CREATE  TABLE IF NOT EXISTS `manager-tran-school`.`USUARIO` (
  `ID_USUARIO` INT NOT NULL AUTO_INCREMENT ,
  `TX_LOGIN` VARCHAR(12) NOT NULL ,
  `TX_SENHA` VARCHAR(12) NOT NULL ,
  `TIPO_USUARIO` INT NOT NULL ,
  `TX_NOME` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID_USUARIO`) ,
  UNIQUE INDEX `UK_TX_LOGIN` (`TX_LOGIN` ASC) )
ENGINE = InnoDB
COMMENT = 'Tabela responsavel pelos dados dos usuários do sistema.';


-- -----------------------------------------------------
-- Table `manager-tran-school`.`ESTADO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `manager-tran-school`.`ESTADO` ;

CREATE  TABLE IF NOT EXISTS `manager-tran-school`.`ESTADO` (
  `ID_ESTADO` INT NOT NULL AUTO_INCREMENT ,
  `TX_ABREVIACAO` CHAR(2) NOT NULL ,
  `TX_DESCRICAO` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID_ESTADO`) )
ENGINE = InnoDB
COMMENT = 'Tabela responsavel pelos dados dos estados brasileiros.';


-- -----------------------------------------------------
-- Table `manager-tran-school`.`CIDADE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `manager-tran-school`.`CIDADE` ;

CREATE  TABLE IF NOT EXISTS `manager-tran-school`.`CIDADE` (
  `ID_CIDADE` INT NOT NULL AUTO_INCREMENT ,
  `ESTADO_ID` INT NOT NULL ,
  `TX_DESCRICAO` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID_CIDADE`) ,
  INDEX `FK_ESTADO` (`ESTADO_ID` ASC) ,
  CONSTRAINT `FK_ESTADO`
    FOREIGN KEY (`ESTADO_ID` )
    REFERENCES `manager-tran-school`.`ESTADO` (`ID_ESTADO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela responsavel pelos dados das cidades dos estados brasi' /* comment truncated */;


-- -----------------------------------------------------
-- Table `manager-tran-school`.`CLIENTE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `manager-tran-school`.`CLIENTE` ;

CREATE  TABLE IF NOT EXISTS `manager-tran-school`.`CLIENTE` (
  `ID_CLIENTE` INT NOT NULL AUTO_INCREMENT ,
  `TX_NOME` VARCHAR(80) NOT NULL ,
  `TX_ENDERECO` VARCHAR(80) NOT NULL ,
  `CIDADE_ID` INT NOT NULL ,
  `CEP` CHAR(8) NULL ,
  `TX_EMAIL` VARCHAR(45) NULL ,
  `CPF` VARCHAR(11) NOT NULL ,
  `TX_BAIRRO` VARCHAR(45) NOT NULL ,
  `NR_TELEFONE_PRIMARIO` VARCHAR(15) NULL ,
  `NR_TELEFONE_SECUNDARIO` VARCHAR(15) NULL ,
  PRIMARY KEY (`ID_CLIENTE`) ,
  UNIQUE INDEX `UK_CPF` (`CPF` ASC) ,
  INDEX `FK_CIDADE` (`CIDADE_ID` ASC) ,
  CONSTRAINT `FK_CIDADE`
    FOREIGN KEY (`CIDADE_ID` )
    REFERENCES `manager-tran-school`.`CIDADE` (`ID_CIDADE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela responsavel pelos dados dos clientes do sistema.';


-- -----------------------------------------------------
-- Table `manager-tran-school`.`LOCALIDADE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `manager-tran-school`.`LOCALIDADE` ;

CREATE  TABLE IF NOT EXISTS `manager-tran-school`.`LOCALIDADE` (
  `ID_LOCALIDADE` INT NOT NULL AUTO_INCREMENT ,
  `NR_LATITUDE` DOUBLE NOT NULL ,
  `NR_LONGITUDE` DOUBLE NOT NULL ,
  `TX_DESCRICAO` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID_LOCALIDADE`) )
ENGINE = InnoDB
COMMENT = 'Tabela responsavel pelos dados das localidades cadastradas n' /* comment truncated */;


-- -----------------------------------------------------
-- Table `manager-tran-school`.`PAGAMENTO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `manager-tran-school`.`PAGAMENTO` ;

CREATE  TABLE IF NOT EXISTS `manager-tran-school`.`PAGAMENTO` (
  `ID_PAGAMENTO` INT NOT NULL AUTO_INCREMENT ,
  `CLIENTE_ID` INT NOT NULL ,
  `TIPO_PAGAMENTO` INT NOT NULL ,
  `VALOR` DECIMAL(7,2) NOT NULL ,
  `DT_VENCIMENTO` DATETIME NOT NULL ,
  PRIMARY KEY (`ID_PAGAMENTO`) ,
  INDEX `FK_CLIENTE` () ,
  CONSTRAINT `FK_CLIENTE`
    FOREIGN KEY ()
    REFERENCES `manager-tran-school`.`CLIENTE` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela responsavel pelos dados do pagamento do cliente.';


-- -----------------------------------------------------
-- Table `manager-tran-school`.`PAGAMENTO_REALIZADO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `manager-tran-school`.`PAGAMENTO_REALIZADO` ;

CREATE  TABLE IF NOT EXISTS `manager-tran-school`.`PAGAMENTO_REALIZADO` (
  `ID_PAGAMENTO_REALIZADO` INT NOT NULL AUTO_INCREMENT ,
  `PAGAMENTO_ID` INT NOT NULL ,
  `DT_PAGAMENTO` DATETIME NOT NULL ,
  `MES_ANO_REFERENTE` DATE NOT NULL ,
  PRIMARY KEY (`ID_PAGAMENTO_REALIZADO`) ,
  INDEX `FK_PAGAMENTO` (`PAGAMENTO_ID` ASC) ,
  CONSTRAINT `FK_PAGAMENTO`
    FOREIGN KEY (`PAGAMENTO_ID` )
    REFERENCES `manager-tran-school`.`PAGAMENTO` (`ID_PAGAMENTO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela responsavel pelos dados de pagamentos realizados pelo' /* comment truncated */;


-- -----------------------------------------------------
-- Table `manager-tran-school`.`ROTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `manager-tran-school`.`ROTA` ;

CREATE  TABLE IF NOT EXISTS `manager-tran-school`.`ROTA` (
  `ID_ROTA` INT NOT NULL AUTO_INCREMENT ,
  `TX_DESCRICAO` VARCHAR(45) NOT NULL ,
  `LOCAL_PARTIDA_ID` INT NOT NULL ,
  PRIMARY KEY (`ID_ROTA`) ,
  UNIQUE INDEX `UK_DESCRICAO` (`TX_DESCRICAO` ASC) ,
  INDEX `FK_LOCALIDADE` (`LOCAL_PARTIDA_ID` ASC) ,
  CONSTRAINT `FK_LOCALIDADE`
    FOREIGN KEY (`LOCAL_PARTIDA_ID` )
    REFERENCES `manager-tran-school`.`LOCALIDADE` (`ID_LOCALIDADE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela responsavel pelos dados das rotas cadastradas no sist' /* comment truncated */;


-- -----------------------------------------------------
-- Table `manager-tran-school`.`ROTA_LOCALIDADE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `manager-tran-school`.`ROTA_LOCALIDADE` ;

CREATE  TABLE IF NOT EXISTS `manager-tran-school`.`ROTA_LOCALIDADE` (
  `ROTA_ID` INT NOT NULL ,
  `LOCALIDADE_ID` INT NOT NULL ,
  PRIMARY KEY (`ROTA_ID`, `LOCALIDADE_ID`) ,
  INDEX `FK_LOCALIDADE` (`LOCALIDADE_ID` ASC) ,
  INDEX `FK_ROTA` (`ROTA_ID` ASC) ,
  CONSTRAINT `FK_ROTA`
    FOREIGN KEY (`ROTA_ID` )
    REFERENCES `manager-tran-school`.`ROTA` (`ID_ROTA` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_LOCALIDADE`
    FOREIGN KEY (`LOCALIDADE_ID` )
    REFERENCES `manager-tran-school`.`LOCALIDADE` (`ID_LOCALIDADE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela responsavel pelo relacionamento entre rotas e localid' /* comment truncated */;


-- -----------------------------------------------------
-- Table `manager-tran-school`.`CLIENTE_LOCALIDADE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `manager-tran-school`.`CLIENTE_LOCALIDADE` ;

CREATE  TABLE IF NOT EXISTS `manager-tran-school`.`CLIENTE_LOCALIDADE` (
  `CLIENTE_ID` INT NOT NULL ,
  `LOCALIDADE_ID` INT NOT NULL ,
  PRIMARY KEY (`CLIENTE_ID`, `LOCALIDADE_ID`) ,
  INDEX `FK_LOCALIDADE` (`LOCALIDADE_ID` ASC) ,
  INDEX `FK_CLIENTE` (`CLIENTE_ID` ASC) ,
  CONSTRAINT `FK_CLIENTE`
    FOREIGN KEY (`CLIENTE_ID` )
    REFERENCES `manager-tran-school`.`CLIENTE` (`ID_CLIENTE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_LOCALIDADE`
    FOREIGN KEY (`LOCALIDADE_ID` )
    REFERENCES `manager-tran-school`.`LOCALIDADE` (`ID_LOCALIDADE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela responsavel pelos relacionamentos entre clientes e lo' /* comment truncated */;


-- -----------------------------------------------------
-- Table `manager-tran-school`.`ROTA_HISTORICO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `manager-tran-school`.`ROTA_HISTORICO` ;

CREATE  TABLE IF NOT EXISTS `manager-tran-school`.`ROTA_HISTORICO` (
  `ID_ROTA_HISTORICO` INT NOT NULL AUTO_INCREMENT ,
  `ROTA_ID` INT NOT NULL ,
  `DT_ROTA` DATETIME NOT NULL ,
  `DURACAO` TIME NULL ,
  `USUARIO_ID` INT NOT NULL ,
  PRIMARY KEY (`ID_ROTA_HISTORICO`) ,
  INDEX `FK_ROTA` (`ROTA_ID` ASC) ,
  INDEX `FK_USUARIO` (`USUARIO_ID` ASC) ,
  CONSTRAINT `FK_ROTA`
    FOREIGN KEY (`ROTA_ID` )
    REFERENCES `manager-tran-school`.`ROTA` (`ID_ROTA` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USUARIO`
    FOREIGN KEY (`USUARIO_ID` )
    REFERENCES `manager-tran-school`.`USUARIO` (`ID_USUARIO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela responsavel pelos dados de histórico de rotas efetuad' /* comment truncated */;


-- -----------------------------------------------------
-- Table `manager-tran-school`.`CLIENTE_ROTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `manager-tran-school`.`CLIENTE_ROTA` ;

CREATE  TABLE IF NOT EXISTS `manager-tran-school`.`CLIENTE_ROTA` (
  `CLIENTE_ID` INT NOT NULL ,
  `ROTA_ID` INT NOT NULL ,
  PRIMARY KEY (`CLIENTE_ID`, `ROTA_ID`) ,
  INDEX `FK_ROTA` (`ROTA_ID` ASC) ,
  INDEX `FK_CLIENTE` (`CLIENTE_ID` ASC) ,
  CONSTRAINT `FK_CLIENTE`
    FOREIGN KEY (`CLIENTE_ID` )
    REFERENCES `manager-tran-school`.`CLIENTE` (`ID_CLIENTE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ROTA`
    FOREIGN KEY (`ROTA_ID` )
    REFERENCES `manager-tran-school`.`ROTA` (`ID_ROTA` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela responsavel pelos relacionamentos entre clientes e ro' /* comment truncated */;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
