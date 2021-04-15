package procesarExcel;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import datos.Conexion;
import controlBackJDBC.controller;
import java.sql.SQLException;

public class Test2 {

    static datesControl datCtrl = new datesControl();
    static controller cont = new controller();
//recibe ruta de archivo a leer, tipo de cartera e id de archivo insertado en la tabla

    public static void lee(String ruta, int typeCartera, int idArchi) throws Exception {
        File test = new File(".");
        //test.getAbsolutePath()+"/src/main/resources/empty_cell Chinese name.xlsx";
        OPCPackage pkg = OPCPackage.open(ruta);
        XSSFReader r = new XSSFReader(pkg);
        InputStream in = r.getSheet("rId1");
        //View the converted xml original file to facilitate understanding of the processing during subsequent parsing,
        //Note: If you open the comment, the following parse() cannot read the content of the stream
        //Test2.streamOut(in);
        //The following is the SST index will be used
        SharedStringsTable sst = r.getSharedStringsTable();
        //sst.writeTo(System.out);
        XMLReader parser = XMLReaderFactory.createXMLReader();//"org.apache.xerces.parsers.SAXParser"
        List<List<String>> container = new ArrayList<>();//Lista de listas
//envia la hoja al analisis
        parser.setContentHandler(new Myhandler(sst, container));
        InputSource inputSource = new InputSource(in);
        parser.parse(inputSource);
        in.close();
//obtiene los valores
        switch (typeCartera) {
            case 1://HEAT
                System.err.println("entro a TEST2 HEAT */-*/- 9- */-  -* 8 -* /8");
                Test2.printContainerSepararIRDSHEAT(container, idArchi);
                List<List<String>> nuevasHEAT = cont.getNewsCuentas();

                Test2.printContainerSepararIRDSHEAT(nuevasHEAT, -1);
                 cont.endTruncateAux();

                break;
            case 2://OFSC
                System.err.println("entro a TEST2 OFSC */-*/- 9- */-  -* 8 -* /8");
                Test2.printContainerSepararIrdsOFSC(container, idArchi);
                //Realiza busqueda de nuevos y los inserta
                List<List<String>> nuevas = cont.getNewsCuentas();
                Test2.printContainerSepararIrdsOFSC(nuevas, -1);
                cont.endTruncateAux();
                break;
            case 3://cobranzaCampo
                break;
            case 4://ModemsEspecial
                break;
            case 5://Urgente
                break;
            default:
                break;
        }

    }

    //para OFSC acumulado general llena correctamente tablas
    public static void printContainerSepararIrdsOFSC(List<List<String>> container, int idArchivon) throws SQLException {
        int contador = cont.getLastdtosCli(),
                val = 0;//controla list externa filas del documento
        boolean bandera = true;//existen registros en tabla principal
        if (contador == -1) {//tabla datosclientes vacia
            contador = 0;
            bandera = false;
        }
        System.err.println("cont Tamoaoakoajosj*-*-*-*//*-*-*-*-*: " + container.size());
        String aux = "",
                auxTel = "",
                cadenaInSert = "";
//        System.err.println("tam: " + container.size());
        for (List<String> stringList : container) {
            if (val > 0 && contador > 0) { //&& contador < 150
                cadenaInSert += "('" + Integer.toString(contador) + "','";
                //  cadenaInSerTel += "('" + Integer.toString(contador) + "','";
                aux = cad1Tab1OFSC(stringList, idArchivon);
                cadenaInSert += aux + "'),\n";
            }//if
            contador++;//indice de tabla sql devuelta
            val++;//indice de archivo actual
            if (val % 150 == 0 || val == container.size()) {//&& acum != 0 
                cadenaInSert = cadenaInSert.replaceFirst(".$", "");//datoscliente
                if (bandera && idArchivon != -1) {
                    cont.GuardaPart1datosClienteAux(cadenaInSert, 0);//0=datoscliente tabla Auxiliar
                } else {
                    System.err.println("bandera && idArchivon != -1 else  */*/*/*/-/-/**/-/--*/-*/-*/-*");
                    cont.GuardaPart1datosCliente(cadenaInSert, 0);//0=datoscliente tabla principal
                }
                //System.out.print(cadenaInSert + " % ");
                cadenaInSert = "";
            }
        }
    }

    //para HEAT acumulado general llena correctamente tablas
    public static void printContainerSepararIRDSHEAT(List<List<String>> container, int idArchivon) throws SQLException {
        int contador = cont.getLastdtosCli(),
                val = 0;//controla list externa filas del documento
        boolean bandera = true;//existen registros en tabla principal
        if (contador == -1) {//tabla datosclientes vacia
            contador = 0;
            bandera = false;
        }
        System.err.println("cont Tamoaoakoajosj*-*-*-*//*-*-*-*-*: " + container.size());
        String aux = "",
                auxTel = "",
                cadenaInSert = "";
//        System.err.println("tam: " + container.size());
        for (List<String> stringList : container) {
            if (val > 0 && contador > 0) { //&& contador < 150
                cadenaInSert += "('" + Integer.toString(contador) + "','";
                //  cadenaInSerTel += "('" + Integer.toString(contador) + "','";
                aux = cad1Tab1HEAT(stringList, idArchivon);
                cadenaInSert += aux + "'),\n";
            }//if
            contador++;//indice de tabla sql devuelta
            val++;//indice de archivo actual
            if (val % 150 == 0 || val == container.size()) {//&& acum != 0 
                cadenaInSert = cadenaInSert.replaceFirst(".$", "");//datoscliente
                if (bandera && idArchivon != -1) {
                    cont.GuardaPart1datosClienteAux(cadenaInSert, 0);//0=datoscliente tabla Auxiliar
                } else {
                    System.err.println("!bandera || idArchivon = -1 else  */*/*/*/-/-/**/-/--*/-*/-*/-*");
                    cont.GuardaPart1datosCliente(cadenaInSert, 0);//0=datoscliente tabla principal
                }
                System.out.print(cadenaInSert + " % ");
                cadenaInSert = "";
            }
        }//for
    }

    /*FOR INTERNO:
                    for (String str : stringList) {
//                    if(stringList.indexOf(str) == 0)
                    /*              if(str.isEmpty()){
                        str = "Vacion";
                    }
                    if(str.contains("|")){
                   //     System.err.println("hay que separarr");
                        str=str.concat(" Separar");
                    }*/
    //System.out.print(stringList.indexOf(str)+" | ");
    // System.out.printf("%10s", str + " | ");
    // }//for_interno*/
    //Read the stream, view the contents of the file xml to analize
    //Llena tabla 1 datoscliente con HEAT
    public static String cad1Tab1HEAT(List<String> param, int idArch) {
        System.out.println("tamañon: -*-**-*-*-**-/" + param.size()+" ID aRCHIVON : -*--*/- "+idArch);
        String cad = "";
        if (idArch != -1) {
            cad += param.get(0) + "','";
            cad += param.get(1) + "','";
            cad += param.get(3) + "','";
            cad += param.get(4) + "','";
            cad += datCtrl.setDateActualGuion() + "','";//fecha del sistema
            cad += param.get(2) + "','";
            cad += param.get(5) + "','";
            cad += param.get(32) + "','";
            cad += param.get(33) + "','";
            cad += param.get(34) + "','";
            cad += param.get(35) + "','";
            cad += param.get(36) + "','";
            cad += param.get(37) + "','";
            cad += param.get(38) + "','";
            cad += param.get(39) + "','";
            cad += param.get(40) + "','";
            cad += param.get(42) + "','";
            cad += param.get(43) + "','";
            cad += param.get(44) + "','";
            cad += param.get(65) + "','";
            cad += param.get(66) + "','";
            cad += param.get(67) + "','";
            cad += param.get(19) + "','";
            cad += datCtrl.setDateActualGuion() + "','";     //Fecha Estatus SS
            cad += "" + "','";
            cad += "" + "','";
            cad += Integer.toString(idArch);//27
        } else {
            System.out.println("entro val archvon HEAT-**-*--**: "+idArch);
            cad += param.get(0) + "','";
            cad += param.get(1) + "','";
            cad += param.get(2) + "','";
            cad += param.get(3) + "','";
            cad += param.get(4) + "','";
            cad += param.get(5) + "','";
            cad += param.get(6) + "','";
            cad += param.get(7) + "','";
            cad += param.get(8) + "','";
            cad += param.get(9) + "','";
            cad += param.get(10) + "','";
            cad += param.get(11) + "','";
            cad += param.get(12) + "','";
            cad += param.get(13) + "','";
            cad += param.get(14) + "','";
            cad += param.get(15) + "','";
            cad += param.get(16) + "','";
            cad += param.get(17) + "','";
            cad += param.get(18) + "','";
            cad += param.get(19) + "','";
            cad += param.get(20) + "','";
            cad += param.get(21) + "','";
            cad += param.get(22) + "','";
            cad += param.get(23) + "','";
            cad += param.get(24) + "','";
            cad += param.get(25) + "','";
            cad += param.get(26);//27
        }
        return cad;
    }

    public static String[] arrayTab1HEAT(List<String> param, int idArch) {
        String[] cad = new String[2];
        if (idArch != -1) {
            cad[0] += param.get(0) + "','";
            cad[0] += param.get(1) + "','";
            cad[0] += param.get(3) + "','";
            cad[0] += param.get(4) + "','";
            cad[0] += datCtrl.setDateActualGuion() + "','";//fecha del sistema
            cad[0] += param.get(2) + "','";
            cad[0] += param.get(5) + "','";
            cad[0] += param.get(32) + "','";
            cad[0] += param.get(33) + "','";
            cad[0] += param.get(34) + "','";
            cad[0] += param.get(35) + "','";
            cad[0] += param.get(36) + "','";
            cad[0] += param.get(37) + "','";
            cad[0] += param.get(38) + "','";
            cad[0] += param.get(39) + "','";
            cad[0] += param.get(40) + "','";
            cad[0] += param.get(42) + "','";
            cad[0] += param.get(43) + "','";
            cad[0] += param.get(44) + "','";
            cad[0] += param.get(65) + "','";
            cad[0] += param.get(66) + "','";
            cad[0] += param.get(67) + "','";
            cad[0] += param.get(19) + "','";
            cad[0] += datCtrl.setDateActualGuion() + "','";     //Fecha Estatus SS
            cad[0] += "" + "','";
            cad[0] += "" + "','";
            cad[0] += Integer.toString(idArch);//inserta idDearchivo donde se saca ese registro
        } else {
            System.out.println("Entro a archivon -1 2222foijf/-*/-*/-*///-*/-/-/-/--/-/-/*-/");
            cad[0] += param.get(0) + "','";
            cad[0] += param.get(1) + "','";
            cad[0] += param.get(2) + "','";
            cad[0] += param.get(3) + "','";
            cad[0] += param.get(4) + "','";
            cad[0] += param.get(5) + "','";
            cad[0] += param.get(6) + "','";
            cad[0] += param.get(7) + "','";
            cad[0] += param.get(8) + "','";
            cad[0] += param.get(9) + "','";
            cad[0] += param.get(10) + "','";
            cad[0] += param.get(11) + "','";
            cad[0] += param.get(12) + "','";
            cad[0] += param.get(13) + "','";
            cad[0] += param.get(14) + "','";
            cad[0] += param.get(15) + "','";
            cad[0] += param.get(16) + "','";
            cad[0] += param.get(17) + "','";
            cad[0] += param.get(18) + "','";
            cad[0] += param.get(19) + "','";
            cad[0] += param.get(20) + "','";
            cad[0] += param.get(21) + "','";
            cad[0] += param.get(22) + "','";
            cad[0] += param.get(23) + "','";
            cad[0] += param.get(24) + "','";
            cad[0] += param.get(25) + "','";
            cad[0] += param.get(26);//27
        }
        /*     cad[1] = param.get(0) + "','";
        cad[1] += param.get(1) + "','";
        cad[1] += param.get(6) + "','";
        cad[1] += param.get(7) + "','";
        cad[1] += param.get(8) + "','";
        cad[1] += "" + "','";
        cad[1] += "" + "','";
        cad[1] += param.get(55) + "','";
        cad[1] += param.get(56) + "','";
        cad[1] += param.get(57) + "','";
        cad[1] += param.get(58) + "','";
        cad[1] += param.get(59) + "'";*/
        return cad;
    }

    //Llena tabla 1 datoscliente con OFSC
    public static String cad1Tab1OFSC(List<String> param, int paramIdArch) {
        String cad = "";
        if (paramIdArch != -1) {
            cad += param.get(1) + "','";
            cad += param.get(0) + "','";
            cad += "0000-00-00" + "','";
            cad += datCtrl.setDateActualGuion() + "','";
            cad += datCtrl.setDateActualGuion() + "','";//fecha del sistema
            cad += param.get(18) + "','";
            cad += param.get(16) + "','";
            cad += param.get(19) + "','";
            cad += param.get(20) + "','";
            cad += param.get(21) + "','";
            cad += param.get(24) + "','";
            cad += param.get(25) + "','";
            cad += param.get(26) + "','";
            cad += param.get(27) + "','";
            cad += param.get(22) + "','";
            cad += param.get(23) + "','";
            cad += param.get(30) + "','";
            cad += "" + "','";
            cad += "" + "','";
            cad += "" + "','";
            cad += param.get(28) + "','";
            cad += param.get(29) + "','";
            cad += "Abierta" + "','";
            cad += datCtrl.setDateActualGuion() + "','";     //Fecha Estatus SS
            cad += "" + "','";
            cad += "" + "','";
            cad += Integer.toString(paramIdArch);//27
        } else {
            System.out.println("Entro a archivon -1 2222foijf/-*/-*/-*///-*/-/-/-/--/-/-/*-/");
            cad += param.get(0) + "','";
            cad += param.get(1) + "','";
            cad += param.get(2) + "','";
            cad += param.get(3) + "','";
            cad += param.get(4) + "','";
            cad += param.get(5) + "','";
            cad += param.get(6) + "','";
            cad += param.get(7) + "','";
            cad += param.get(8) + "','";
            cad += param.get(9) + "','";
            cad += param.get(10) + "','";
            cad += param.get(11) + "','";
            cad += param.get(12) + "','";
            cad += param.get(13) + "','";
            cad += param.get(14) + "','";
            cad += param.get(15) + "','";
            cad += param.get(16) + "','";
            cad += param.get(17) + "','";
            cad += param.get(18) + "','";
            cad += param.get(19) + "','";
            cad += param.get(20) + "','";
            cad += param.get(21) + "','";
            cad += param.get(22) + "','";
            cad += param.get(23) + "','";
            cad += param.get(24) + "','";
            cad += param.get(25) + "','";
            cad += param.get(26);//27
        }
        return cad;
    }

    public static void streamOut(InputStream in) throws Exception {
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) != -1) {
            System.out.write(buf, 0, len);
            // System.out.println("");
        }
    }

    /*  public static void main(String[] args) {
        Test2 ts = new Test2();
        String file = "A://PRUEBAS SISTEMA/HEAT NEW/01032021/Servicios Aplicados De Cobranza Mercantil Vd Sa De Cv VeTV - Etapa   3.xlsx";
        //  String file = "A://PRUEBAS SISTEMA/OFSC/SERVICIOS_APLICADOS_DE_COBRANZA_MERCANTIL_VD_01032021.xlsx";
        try {
            ts.lee(file);
        } catch (Exception ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}

class Myhandler extends DefaultHandler {

    //Take the value corresponding to the index of SST
    private SharedStringsTable sst;

    public void setSst(SharedStringsTable sst) {
        this.sst = sst;
    }

    //Save analysis results
    private List<List<String>> container;

    public Myhandler(SharedStringsTable sst, List<List<String>> container) {
        this.sst = sst;
        this.container = container;
    }

    private String lastContents;
    //Valid data rectangle area, A1:Y2
    private String dimension;
    //According to the dimension, the data length of each row is obtained
    private int longest;
    //The id of the last cell with content, to determine the empty cell
    private String lastRowid;
    //Row data save
    private List<String> currentRow;
    //The cell content is the index of SST
    private boolean isSSTIndex = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println("startElement:"+qName);

        if (qName.equals("dimension")) {
            dimension = attributes.getValue("ref");
            longest = covertRowIdtoInt(dimension.substring(dimension.indexOf(":") + 1));
        }
        //Start of line
        if (qName.equals("row")) {
            currentRow = new ArrayList<>();
        }
        if (qName.equals("c")) {
            String rowId = attributes.getValue("r");
//             System.out.println(attributes.getValue("r") + " - ");//r=celda, v= value de celda 
            //Null cell judgment, add empty characters to the list
            if (lastRowid != null) {
                int gap = covertRowIdtoInt(rowId) - covertRowIdtoInt(lastRowid);
                for (int i = 0; i < gap - 1; i++) {
                    currentRow.add("");
                }
            } else {
                //The first cell may not be in the first column
                if (!"A1".equals(rowId)) {
                    for (int i = 0; i < covertRowIdtoInt(rowId) - 1; i++) {
                        currentRow.add("");
                    }
                }
            }
            lastRowid = rowId;
            //Judging that the value of the cell is the index of SST, and the characters method cannot be used directly
            if (attributes.getValue("t") != null && attributes.getValue("t").equals("s")) {
                isSSTIndex = true;
            } else {
                isSSTIndex = false;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println("endElement:"+qName);
        //End of line, store one line of data
        if (qName.equals("row")) {
            // System.out.println("");
            //Determine whether the last cell is at the end and fill in the number of columns
            if (covertRowIdtoInt(lastRowid) < longest) {
                for (int i = 0; i < longest - covertRowIdtoInt(lastRowid); i++) {
                    currentRow.add("");
                }
            }
            container.add(currentRow);
            lastRowid = null;
        }
        //The cell content tag ends, the characters method will be called to process the content
        if (qName.equals("v")) {
            //The value of the cell is the index of SST
            if (isSSTIndex) {
                String sstIndex = lastContents.toString();
                try {
                    int idx = Integer.parseInt(sstIndex);
                    XSSFRichTextString rtss = new XSSFRichTextString(
                            sst.getEntryAt(idx));
                    lastContents = rtss.toString();
                    currentRow.add(lastContents);
                } catch (NumberFormatException ex) {
                    System.out.println(lastContents);
                }
            } else {
                currentRow.add(lastContents);
            }
        }
    }

    /**
     * Get element text data
     */
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        lastContents = new String(ch, start, length);
    }

    /**
     * Column number to number AB7-->28 Column 28
     *
     * @param rowId
     * @return
     */
    public static int covertRowIdtoInt(String rowId) {
        int firstDigit = -1;
        for (int c = 0; c < rowId.length(); ++c) {
            if (Character.isDigit(rowId.charAt(c))) {
                firstDigit = c;
                break;
            }
        }
        //AB7-->AB
        //AB is the column number, 7 is the row number
        String newRowId = rowId.substring(0, firstDigit);
        int num = 0;
        int result = 0;
        int length = newRowId.length();
        for (int i = 0; i < length; i++) {
            //Take the lowest bit first, B
            char ch = newRowId.charAt(length - i - 1);
            //B represents the decimal number 2, and the ascii code is subtracted. Based on the ascii code of A, A represents 1, B represents 2
            num = (int) (ch - 'A' + 1);
            //The column number conversion is equivalent to the conversion of 26 hexadecimal numbers to decimal
            num *= Math.pow(26, i);
            result += num;
        }
        return result;

    }
    /*    public static void main(String[] args) {
        //Myhandler mh = new Myhandler(sst, container)
        System.out.println(Myhandler.covertRowIdtoInt("AB7"));
    }
     */
}
