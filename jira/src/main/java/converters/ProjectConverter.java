///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package converters;
//
//import java.lang.annotation.Annotation;
//import javax.faces.convert.FacesConverter;
//import javax.persistence.Converter;
//
///**
// *
// * @author Oana
// */
//@FacesConverter("defConverter")
//public class ProjectConverter implements Converter
//{
//    
//    @EJB
//    private DefFacade defFacade;
//
//    @Override
//    public Object getAsObject(FacesContext fc, UIComponent uic, String string)
//    {
//        LOG.info("getAsObject: " + string);
//        try
//        {
//            return defFacade.findWithNFieldsWithValue("name", string, "=").get(0);
//        }
//        catch (Exception ex)
//        {
//            LOG.log(Level.SEVERE, "Error while fetching Def for " + string, ex);
//        }
//        return null;
//    }
//
//    @Override
//    public String getAsString(FacesContext fc, UIComponent uic, Object obj)
//    {
//        LOG.info("getAsString obj class: " + obj.getClass().getName());
//        if(obj instanceof Def)
//        {
//            Def def = (Def)obj;
//            LOG.info("getAsString def name: " + def.getName());
//            return def.getName();
//        }
//        else
//        {
//            StringBuilder sbError = new StringBuilder("The object of class ");
//            sbError.append(obj.getClass().getName()).append(" is not of Def");
//            throw new ClassCastException(sbError.toString());
//        }
//    }
//
//    @Override
//    public boolean autoApply() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Class<? extends Annotation> annotationType() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}