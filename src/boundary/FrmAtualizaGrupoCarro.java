

package boundary;

import control.ControladoraGrupoCarro;
import util.ConexaoException;


class FrmAtualizaGrupoCarro extends FrmCadastroGrupoCarro{


    public FrmAtualizaGrupoCarro(javax.swing.JFrame parent, boolean modal, ControladoraGrupoCarro controladora) throws ConexaoException {
        super(parent, modal);
        this.controladoraGrupoCarro = controladora;

    }

}
