package com.cepibase.cepitaxis;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class AnalizadorXMLDom {
    InputSource entrada;

    public AnalizadorXMLDom(InputSource entrada){
        this.entrada=entrada;
    }
    public ArrayList<Taxi>parse(){
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        ArrayList<Taxi> listaTaxis=new ArrayList<>();

        Document documentoDom=null;
        try {
            DocumentBuilder builder=factory.newDocumentBuilder();
            documentoDom=builder.parse(entrada);
        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (SAXException e){
            e.printStackTrace();
        }
        Element elementoXML=documentoDom.getDocumentElement();
        NodeList nodosTaxiXML=elementoXML.getElementsByTagName("taxi");
        for (int i=0; i<nodosTaxiXML.getLength();i++){
            Taxi taxi=new Taxi();
            Node taxiActual=nodosTaxiXML.item(i);

            taxi.setMatricula(
                    taxiActual.getChildNodes().item(0).getFirstChild().getNodeValue());
            taxi.setLat(Double.parseDouble(
                    taxiActual.getChildNodes().item(1).getFirstChild().getNodeValue()));
            taxi.setLng(Double.parseDouble(
                    taxiActual.getChildNodes().item(2).getFirstChild().getNodeValue()));
            taxi.setDisponible(true);
            listaTaxis.add(taxi);
        }
        return listaTaxis;
    }
}
