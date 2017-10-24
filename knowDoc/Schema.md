# XML Schema（feiman）  

## 1.Schema缘由   

### 1.DTD作用   

> DTD实际上可以看作一个或多个XML文件的模板，这些XML文件中的元素、元素的属性、元素的排列方式/顺序、元素能够包含的内容等，都必须符合DTD中的定义。  

1. dtd文件的引用  

```xml
<?xml version="1.0"?>

<!DOCTYPE note SYSTEM
"http://www.w3schools.com/dtd/note.dtd">

<note>
  <to>Tove</to>
  <from>Jani</from>
  <heading>Reminder</heading>
  <body>Don't forget me this weekend!</body>
</note>
```

2. note.dtd定义案例   

```xml
<!ELEMENT note (to, from, heading, body)>
<!ELEMENT to (#PCDATA)>
<!ELEMENT from (#PCDATA)>
<!ELEMENT heading (#PCDATA)>
<!ELEMENT body (#PCDATA)>
```

说明标签的元素的类型为 "#PCDATA"     













### 2.Schema  

- XML Schema 是基于 XML 的 DTD 替代者。   

- Schema实例 : 配置模板        

  ```xml  
  <?xml version="1.0"?>
  <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
  targetNamespace="http://www.w3schools.com"
  xmlns="http://www.w3schools.com"
  elementFormDefault="qualified">

  <xs:element name="note">
    <xs:complexType>
      <xs:sequence>
        <xs:element name="to" type="xs:string"/>
        <xs:element name="from" type="xs:string"/>
        <xs:element name="heading" type="xs:string"/>
        <xs:element name="body" type="xs:string"/>
      </xs:sequence>
    </xs:complexType>
  </xs:element>

  </xs:schema>
  ```

  - <schema> 元素是每一个 XML Schema 的根元素   

  1. xmlns:xs=指定命名空间，与前缀为xs    
  2. targetNamespace=定义标签来自于该地址   
  3. xmlns=指向默认命名空间  
  4. elementFormDefault=说明使用该schema的文件就会被命名空间限定   

- Schema的引用：

```xml
<?xml version="1.0"?>

<note
xmlns="http://www.w3schools.com"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.w3schools.com note.xsd">
  <to>Tove</to>
  <from>Jani</from>
  <heading>Reminder</heading>
  <body>Don't forget me this weekend!</body>
</note>
```

通过三条代码片段引用 Schema   





Next:   

http://www.runoob.com/schema/schema-simple.html

