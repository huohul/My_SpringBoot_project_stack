package com.gxweb;

import com.gxweb.entity.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ： CYQ
 * @date ：Created in 2021/2/10/010 19:00
 * @description ：测试类  2万字20个实例解析Java8 Stream，带你玩转集合四大点！
 * @version: 1.0  https://mp.weixin.qq.com/s/8eC5KQab7FPixM4THPd2qw
 */
public class StreamTest {

    @Test
    public void stmtest() {
        List<Integer> list = Arrays.asList(6, 23, 532, 5235, 252, 3421, 321, 1, 41, 5);
//        遍历输出符合条件的元素 > 6   的
        list.stream().filter(x -> x > 6).forEach(System.out::println);

        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();

        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();

        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 1);

        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在小于1的值：" + anyMatch);
    }

    //  案例一：筛选出Integer集合中大于7的元素，并打印出来
    @Test
    public void CaseNumberOne() {
        List<Integer> list = Arrays.asList(142, 1, 412, 5, 124, 1, 25, 14);
        list.stream().filter(x -> x > 7).forEach(System.out::println);
    }

    //    案例二：筛选员工中工资高于8000的人，并形成新的集合。 形成新集合依赖collect
    @Test
    public void CaseNumberTwo() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        List<String> collect = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
        System.out.println("高于8000的员工的姓名是:" + collect);
    }

    /**
     * 3.3 聚合（max/min/count)
     * max、min、count这些字眼你一定不陌生，没错，在mysql中我们常用它们进行数据统计。
     * Java stream中也引入了这些概念和用法，极大地方便了我们对集合、数组的数据统计工作。
     */
//    案例一：获取String集合中最长/大的元素。
    @Test
    public void CaseNumberOneMax() {
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        List<Integer> listsz = Arrays.asList(142, 1, 412, 5, 124, 1, 25, 14);
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        Optional<Integer> max1 = listsz.stream().max(Comparator.comparing(Integer::valueOf));
        System.out.println("最长字符串:" + max + "---" + max1.get());

    }

    //    案例二：获取Integer集合中的最大值. 并附排序
    @Test
    public void CaseNumberTwoSort() {
        List<Integer> list = Arrays.asList(7, 6, 9, 4, 11, 6);
        // 自然排序
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        //自定义排序
        Optional<Integer> max1 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println("自然排序的最大值是:" + max.get());
        System.out.println("自定义排序的最大值是:" + max1.get());
    }

    //    案例三：获取员工工资最高的人。
    @Test
    public void CaseNumberThree() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));
        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("工资最高的是:" + max.get());
    }

    //    案例四：计算Integer集合中大于6的元素的个数
    @Test
    public void CaseNumberFour() {
        List<Integer> list = Arrays.asList(1, 1, 1, 1, 2, 41, 512, 512, 5);
        long count = list.stream().filter(x -> x > 6).count();
        System.out.println("list中大于6的元素个数为= " + count);
    }

    /**
     * 3.4 映射(map/flatMap)
     * 映射，可以将一个流的元素按照一定的映射规则映射到另一个流中。分为map和flatMap：
     * <p>
     * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
     */
//    案例一：英文字符串数组的元素全部改为大写。整数数组每个元素+3。
    @Test
    public void tstasm3_4() {
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
        List<String> stringList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("字符串大写= " + stringList);
        List<Integer> list = Arrays.asList(3, 2, 34, 2, 21, 56, 1, 41);
        List<Object> list1 = list.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println("Integer数组 每个元素+3= " + list1);

    }

    //案例二：将员工的薪资全部增加1000。
    @Test
    public void samaljia() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        //不改变原来员工集合的方式
        List<Person> collect = personList.stream().map(person -> {
            Person person1 = new Person(person.getName(), person.getSalary(), person.getAge(), person.getSex(), person.getArea());
            person1.setSalary(person.getSalary() + 1000);
            return person1;
        }).collect(Collectors.toList());
        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("一次改动后：" + collect);
    }

    //    案例三：将两个字符数组合并成一个新的字符数组。
    @Test
    public void samaltoStrArr() {
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> stringStream = list.stream().flatMap(s -> {
            //将每一个元素转换成strem
            String[] split = s.split(",");
            Stream<String> stream = Arrays.stream(split);
            return stream;

        }).collect(Collectors.toList());
        System.out.println("处理前的集合= " + list);
        System.out.println("处理后的集合= " + stringStream);
    }

    /**
     * 3.5 归约(reduce)
     * 归约，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
     */
//    案例一：求Integer集合的元素之和、乘积和最大值。
    @Test
    public void sumAndMax() {
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
//        求和方式一
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
//        求和方式二
        Optional<Integer> sum1 = list.stream().reduce(Integer::sum);
//        求和方式三
        Integer sum2 = list.stream().reduce(0, Integer::sum);

        //求乘积
        Optional<Integer> reduce = list.stream().reduce((x, y) -> x * y);
        //求最大方式一
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);

//        求最大方式二
        Integer max1 = list.stream().reduce(1, Integer::max);

        System.out.println("list和 = " + sum.get() + "--" + sum1.get() + "---" + sum2);

        System.out.println("乘积: " + reduce.get());
        System.out.println("最大值 " + max.get() + "=-=-=-" + max1);
    }

    //    案例二：求所有员工的工资之和和最高工资。
    @Test
    public void salmaxsum() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        //求工资之和方式1
        Optional<Integer> sumSal1 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        //求工资之和方式2
        Integer sumSal2 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), (sum1, sum2) -> sum1 + sum2);
        //求工资之和方式3
        Integer sumSal3 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);

        //求最高工资方式1
        Integer maxSal1 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(), Integer::max);
        //求最高工资方式2
        Integer maxSal2 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(), (max1, max2) -> max1 > max2 ? max1 : max2);

        System.out.println("sumSal1 = " + sumSal1.get() + "-->sumSam2=" + sumSal2 + "-->samSal3=" + sumSal3);

        System.out.println("maxSal1+\"最高工资:\"+maxSal2 = " + maxSal1 + "," + maxSal2);

    }

    /**
     * 3.6 收集(collect)
     * collect，收集，可以说是内容最繁多、功能最丰富的部分了。从字面上去理解，就是把一个流收集起来，最终可以是收集成一个值也可以收集成一个新的集合。
     * <p>
     * collect主要依赖java.util.stream.Collectors类内置的静态方法。
     * <p>
     * 3.6.1 归集(toList/toSet/toMap)
     * 因为流不存储数据，那么在流中的数据完成处理后，需要将流中的数据重新归集到新的集合里。toList、toSet和toMap比较常用，另外还有toCollection、toConcurrentMap等复杂一些的用法。
     */
//    下面用一个案例演示toList、toSet和toMap：
    @Test
    public void testtoColl() {
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> list1 = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());

        Set<Integer> set = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));

        Map<String, Person> personMap = personList.stream().filter(p -> p.getSalary() > 8000).collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("list1 = " + list1);
        System.out.println("set = " + set);
        System.out.println("personMap = " + personMap);
    }

    /**
     * 3.6.2 统计(count/averaging)
     * Collectors提供了一系列用于数据统计的静态方法：
     * <p>
     * 计数：count
     * 平均值：averagingInt、averagingLong、averagingDouble
     * 最值：maxBy、minBy
     * 求和：summingInt、summingLong、summingDouble
     * 统计以上所有：summarizingInt、summarizingLong、summarizingDouble
     */
//    案例：统计员工人数、平均工资、工资总额、最高工资。
    @Test
    public void StreamTestavgsummax() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        // 求总数
        Long count = personList.stream().collect(Collectors.counting());
        // 求平均工资
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        // 求最高工资
        Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        // 求工资之和
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        // 一次性统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));

        System.out.println("员工总数：" + count);
        System.out.println("员工平均工资：" + average);
        System.out.println("员工工资总和：" + sum);
        System.out.println("员工工资所有统计：" + collect);
    }


    /**
     * 3.6.3 分组(partitioningBy/groupingBy)
     * 分区：将stream按条件分为两个Map，比如员工按薪资是否高于8000分为两部分。
     * 分组：将集合分为多个Map，比如员工按性别分组。有单级分组和多级分组。
     */
//    案例：将员工按薪资是否高于8000分为两部分；将员工按性别和地区分组
    public void StreamTestgraup() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));

        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按性别分组情况：" + group);
        System.out.println("员工按性别、地区：" + group2);
    }

    /**
     * 3.6.4 接合(joining)
     * joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
     */
    public void testStmjoining() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
    }

    /**
     * 3.6.5 归约(reducing)
     * Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
     */
    @Test
    public void testStmguiyue() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 5000)));
        System.out.println("员工扣税薪资总和：" + sum);

        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());
    }

    /**
     * 3.7 排序(sorted)
     * sorted，中间操作。有两种排序：
     * <p>
     * sorted()：自然排序，流中元素需实现Comparable接口
     * sorted(Comparator com)：Comparator排序器自定义排序
     */
//    案例：将员工按工资由高到低（工资一样则按年龄由大到小）排序
    @Test
    public void testStmSort() {
        List<Person> personList = new ArrayList<Person>();

        personList.add(new Person("Sherry", 9000, 24, "female", "New York"));
        personList.add(new Person("Tom", 8900, 22, "male", "Washington"));
        personList.add(new Person("Jack", 9000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 8800, 26, "male", "New York"));
        personList.add(new Person("Alisa", 9000, 26, "female", "New York"));

        // 按工资升序排序（自然排序）
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = personList.stream()
                .sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());

        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    }

    /**
     * 3.8 提取/组合
     * 流也可以进行合并、去重、限制、跳过等操作。
     */
    @Test
    public void testStmDis() {
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"d", "e", "f", "g"};

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect);
        System.out.println("skip：" + collect2);
    }
}
