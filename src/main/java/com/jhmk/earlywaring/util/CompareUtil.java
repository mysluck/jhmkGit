package com.jhmk.earlywaring.util;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompareUtil {

    private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");


    /**
     * sort 1正序 -1 倒序  filed 多字段排序
     *
     * @param sort
     * @param filed
     * @param <T>
     * @return
     */
    public static <T> Comparator createComparator(int sort, String... filed) {
        return new ImComparator(sort, filed);
    }

    public static class ImComparator implements Comparator {
        int sort = 1;
        String[] filed;

        public ImComparator(int sort, String... filed) {
            this.sort = sort == -1 ? -1 : 1;
            this.filed = filed;
        }

        @Override
        public int compare(Object o1, Object o2) {
            int result = 0;
            for (String file : filed) {
                Object value1 = ReflexUtil.invokeMethod(file, o1);
                Object value2 = ReflexUtil.invokeMethod(file, o2);
                if (value1 == null || value2 == null) {
                    continue;
                }
                //Integer整数序排序
                if (value1 instanceof Integer) {
                    int v1 = Integer.valueOf(value1.toString());
                    int v2 = Integer.valueOf(value2.toString());
                    if (v1 == v2) {
                        continue;
                    }
                    if (sort == 1) {
                        return v1 - v2;
                    } else if (sort == -1) {
                        return v2 - v1;
                    }
                    //Date类型排序
                } else if (value1 instanceof Date) {
                    Date d1 = (Date) value1;
                    Date d2 = (Date) value2;
                    if (d1.compareTo(d2) == 0) {
                        continue;
                    }
                    if (sort == 1) {
                        return d1.compareTo(d2);
                    } else if (sort == -1) {
                        return d2.compareTo(d1);
                    }
                    //Long排序
                } else if (value1 instanceof Long) {
                    long v1 = Long.valueOf(value1.toString());
                    long v2 = Long.valueOf(value2.toString());
                    if (v1 == v2) {
                        continue;
                    }
                    if (sort == 1) {
                        return v1 > v2 ? 1 : -1;
                    } else if (sort == -1) {
                        return v2 > v1 ? 1 : -1;
                    }
                    //BigDecimal排序
                } else if (value1 instanceof BigDecimal) {
                    BigDecimal v1 = new BigDecimal(value1.toString());
                    BigDecimal v2 = new BigDecimal(value2.toString());
                    if (v1.compareTo(v2) == 0) {
                        continue;
                    }
                    if (sort == 1) {
                        return v1.compareTo(v2);
                    } else if (sort == -1) {
                        return v2.compareTo(v1);
                    }
                    //Double排序
                } else if (value1 instanceof Double) {
                    Double v1 = Double.valueOf(value1.toString());
                    Double v2 = Double.valueOf(value2.toString());
                    if (v1.equals(v2)) {
                        continue;
                    }
                    if (sort == 1) {
                        return v1 > v2 ? 1 : -1;
                    } else if (sort == -1) {
                        return v2 > v1 ? 1 : -1;
                    }
                    //String排序 只适用于yyyyMMdd 或HHmmss格式的日期字符串比较 或者数字字符串比较
                } else if (value1 instanceof String) {
                    //只有数字时才进行比较
                    if (isNumeric(value1.toString()) && isNumeric(value2.toString())) {
                        long v1 = Long.valueOf(value1.toString());
                        long v2 = Long.valueOf(value2.toString());
                        if (v1 == v2) {
                            continue;
                        }
                        if (sort == 1) {
                            return v1 > v2 ? 1 : -1;
                        } else if (sort == -1) {
                            return v2 > v1 ? 1 : -1;
                        }
                    }
                }

            }

            return result;
        }
    }

    public static boolean isNumeric(String str) {
        Matcher isNum = NUMBER_PATTERN.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    public static <T> Comparator createComparatorForMap(int sort, String filed) {
        return new ImComparatorForMap(sort, filed);
    }

    public static class ImComparatorForMap implements Comparator {
        int sort = 1;
        String filed;

        public ImComparatorForMap(int sort, String filed) {
            this.sort = sort == -1 ? -1 : 1;
            this.filed = filed;
        }

        @Override
        public int compare(Object o1, Object o2) {

            Map<String, String> v1 = ((Map) o1);
            Map<String, String> v2 = ((Map) o2);

            if (sort == 1) {
                return v1.get(filed).compareTo(v2.get(filed));
            } else if (sort == -1) {
                return v2.get(filed).compareTo(v1.get(filed));
            } else {
                return v2.get(filed).compareTo(v1.get(filed));
            }
        }
    }


    public static <T> Comparator createComparatorForDate(int sort, String filed) {
        return new ImComparatorForDate(sort, filed);
    }


    public static class ImComparatorForDate implements Comparator {
        int sort = 1;
        String[] filed;

        public ImComparatorForDate(int sort, String... filed) {
            this.sort = sort == -1 ? -1 : 1;
            this.filed = filed;
        }


        @Override
        public int compare(Object o1, Object o2) {
            int result = 0;
            for (String file : filed) {
                Object value1 = ReflexUtil.invokeMethod(file, o1);
                Object value2 = ReflexUtil.invokeMethod(file, o2);
                if (value1 == null || value2 == null) {
                    continue;
                }
//                DateFormatUtil.parseDate(value1,"");
            }
            return 0;
        }
    }

    //按map的value排序
    public static Map<String, Integer> compareMapValue(Map<String, Integer> map) {
        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        };
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, valueComparator);
        Iterator<Map.Entry<String, Integer>> iter = list.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }


}