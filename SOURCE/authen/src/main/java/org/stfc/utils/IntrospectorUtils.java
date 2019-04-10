package org.stfc.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.StringCharacterIterator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author dongdv
 *
 */
public class IntrospectorUtils {

    private static final Logger logger = LogManager.getLogger(IntrospectorUtils.class);
    private static final String EMPTY = "";

    public static void cloneEntity(Object target, Object source) {
        if (target == null || source == null) {
            return;
        }
        try {
            BeanInfo infoTarget = Introspector.getBeanInfo(target.getClass(),
                    Object.class);
            PropertyDescriptor[] proTargetList = infoTarget
                    .getPropertyDescriptors();
            BeanInfo infoSource = Introspector.getBeanInfo(source.getClass(),
                    Object.class);
            PropertyDescriptor[] proSourceList = infoSource
                    .getPropertyDescriptors();
            for (PropertyDescriptor pdTarget : proTargetList) {
                for (PropertyDescriptor pdSource : proSourceList) {
                    if (pdTarget.getName().equalsIgnoreCase(pdSource.getName())) {
                        Method getter = pdSource.getReadMethod();
                        Method setter = pdTarget.getWriteMethod();
                        Object value = getter.invoke(source);
                        if (!Comparator.isEqualNull(value)) {
                            if (Comparator.isEqualNullOrEmpty(String.valueOf(value))) {
                                try {
                                    setter.invoke(target, (Object) null);
                                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                                    logger.error("Error setter value type not match. VALUE: " + value);
                                    if (logger.isDebugEnabled()) {
                                        logger.debug(ex.getMessage(), ex);
                                    }
                                }
                            } else {
                                try {
                                    setter.invoke(target, value);
                                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                                    logger.error("Error setter value type not match. VALUE: " + value);
                                    if (logger.isDebugEnabled()) {
                                        logger.debug(ex.getMessage(), ex);
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }

        } catch (IntrospectionException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            logger.error("Error clone:" + ex.getMessage(), ex);
        }
    }

    /**
     * Thuc hien tien xu ly cho cac kieu du lieu date, number; trim() cho cac
     * field
     *
     * @param obj
     */
    public static void preProcess(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            BeanInfo info = Introspector.getBeanInfo(obj.getClass(),
                    Object.class);
            PropertyDescriptor[] pros = info.getPropertyDescriptors();
            for (PropertyDescriptor pd : pros) {
                Method getter = pd.getReadMethod();
                Object value = getter.invoke(obj);
                if (getter.getReturnType().equals(String.class)) {
                    Method setter = pd.getWriteMethod();
                    if (value != null && value.toString().trim().isEmpty()) {
                        setter.invoke(obj, new Object[]{null});
                    } else if (value != null) {
                        setter.invoke(obj, value.toString().trim());
                    }
                }

            }

        } catch (IllegalArgumentException | IntrospectionException
                | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void doSetNotNull(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            BeanInfo info = Introspector.getBeanInfo(obj.getClass(),
                    Object.class);
            PropertyDescriptor[] pros = info.getPropertyDescriptors();
            for (PropertyDescriptor pd : pros) {
                Method getter = pd.getReadMethod();
                Object value = getter.invoke(obj);
                if (getter.getReturnType().equals(String.class)) {
                    Method setter = pd.getWriteMethod();
                    if (value == null) {
                        setter.invoke(obj, EMPTY);
                    }
                }

            }

        } catch (IllegalArgumentException | IntrospectionException
                | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static String processSpecial(String token) {
        StringBuilder s = new StringBuilder(token.length());
        CharacterIterator it = new StringCharacterIterator(token);
        for (char ch = it.first(); ch != CharacterIterator.DONE; ch = it.next()) {
            switch (ch) {
                case '&':
                    s.append("#%26");
                    break;
                case '<':
                    s.append("#%3C");
                    break;
                case '>':
                    s.append("#%3E");
                    break;
                case '*':
                    s.append("#%2A");
                    break;
                default:
                    s.append(ch);
                    break;
            }
        }

        token = s.toString();
        return token;

    }

    /**
     * Thuc hien tien xu ly cho cac kieu du lieu date, number; trim() Neu args
     * khac rong thuc hien xoa bo cac dau phay (,) cho cac field trong list fied
     * truyen trong args
     *
     * @param obj
     * @param args
     */
    public static void preProcess(Object obj, String args) {
        if (obj == null) {
            return;
        }
        try {
            BeanInfo info = Introspector.getBeanInfo(obj.getClass(),
                    Object.class);
            PropertyDescriptor[] pros = info.getPropertyDescriptors();
            for (PropertyDescriptor pd : pros) {
                Method getter = pd.getReadMethod();
                Object value = getter.invoke(obj);
                if (getter.getReturnType().equals(String.class)) {
                    Method setter = pd.getWriteMethod();
                    if (value != null && value.toString().trim().isEmpty()) {
                        setter.invoke(obj, new Object[]{null});
                    } else if (value != null) {
                        if (args != null && args.contains(pd.getName())) {
                            setter.invoke(obj, value.toString().trim()
                                    .replaceAll(",", EMPTY));
                        } else {
                            setter.invoke(obj, value.toString().trim());
                        }
                    }

                }

            }

        } catch (IllegalArgumentException | IntrospectionException
                | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * convert sang dang so, xu ly cac ky tu dac biet => service nay chi dung
     * trong cac bao cao
     *
     * @param obj
     * @param args
     */
    public static void convertToNumberFormat(Object obj, String args) {
        if (obj == null) {
            return;
        }
        try {
            BeanInfo info = Introspector.getBeanInfo(obj.getClass(),
                    Object.class);
            PropertyDescriptor[] pros = info.getPropertyDescriptors();
            for (PropertyDescriptor pd : pros) {
                Method getter = pd.getReadMethod();
                Object value = getter.invoke(obj);
                if (getter.getReturnType().equals(String.class)) {
                    Method setter = pd.getWriteMethod();
                    if (value == null) {
                        setter.invoke(obj, EMPTY);
                    } else {
                        if (args != null && args.contains(pd.getName())) {
                            setter.invoke(obj, formatToNumber(value.toString()
                                    .trim()));
                        } else {
                            setter.invoke(obj, processSpecial(value.toString()
                                    .trim()));
                        }
                    }
                }

            }

        } catch (IllegalArgumentException | IntrospectionException
                | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static final String DECIMAL_FORMAT = "###,###.##";

    public static String formatToNumber(String valueInStr) {
        if (valueInStr == null || EMPTY.equals(valueInStr)) {
            return null;
        }
        double valueInDouble = Double.valueOf(valueInStr);
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);
        return decimalFormat.format(valueInDouble);
    }
}
