# Chapter 12 — 고급 GUI

> **최종 수정일:** 2026-04-01

> **선수 지식**: [프로그래밍언어] Java (제1-11장). [객체지향] 이벤트 기반 프로그래밍.
>
> **학습 목표**:
> 1. Swing 컴포넌트를 사용하여 GUI 애플리케이션을 구축할 수 있다
> 2. 리스너와 내부 클래스로 이벤트를 처리할 수 있다
> 3. 반응형 UI 설계를 위해 레이아웃 매니저를 적용할 수 있다

---

## 목차

- [1. Swing 개요](#1-swing-개요)
  - [1.1 AWT vs. Swing](#11-awt-vs-swing)
  - [1.2 GUI 클래스 계층 구조](#12-gui-클래스-계층-구조)
- [2. JFrame과 Content Pane](#2-jframe과-content-pane)
  - [2.1 JFrame 생성](#21-jframe-생성)
  - [2.2 JFrame 상속](#22-jframe-상속)
- [3. 배치 관리자](#3-배치-관리자)
  - [3.1 FlowLayout](#31-flowlayout)
  - [3.2 BorderLayout](#32-borderlayout)
  - [3.3 GridLayout](#33-gridlayout)
- [4. 주요 Swing 컴포넌트](#4-주요-swing-컴포넌트)
- [5. 이벤트 처리](#5-이벤트-처리)
  - [5.1 ActionListener](#51-actionlistener)
  - [5.2 익명 클래스와 람다](#52-익명-클래스와-람다)
- [요약](#요약)

---

<br>

## 1. Swing 개요

### 1.1 AWT vs. Swing

| 특징 | AWT | Swing |
|:--------|:----|:------|
| 패키지 | `java.awt` | `javax.swing` |
| 컴포넌트 | 중량(OS 네이티브) | 경량(Java 렌더링) |
| 룩 앤 필 | 플랫폼 종속적 | 플랫폼 독립적 |
| 명명 규칙 | `Button`, `Label` | `JButton`, `JLabel` |

### 1.2 GUI 클래스 계층 구조

```
Object -> Component -> Container -> Window -> Frame -> JFrame
                                          -> Dialog -> JDialog
                    -> JComponent -> JLabel, JButton, JTextField, ...
```

---

<br>

## 2. JFrame과 Content Pane

### 2.1 JFrame 생성

```java
JFrame frame = new JFrame("My Window");
frame.setSize(400, 300);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
```

### 2.2 JFrame 상속

```java
public class MyFrame extends JFrame {
    public MyFrame() {
        setTitle("Welcome to GUI!");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.BLUE);

        setVisible(true);
    }
}
```

---

<br>

## 3. 배치 관리자

### 3.1 FlowLayout

컴포넌트를 왼쪽에서 오른쪽으로 배치하며, 공간이 부족하면 다음 행으로 줄 바꿈한다:

```java
container.setLayout(new FlowLayout());
container.add(new JButton("A"));
container.add(new JButton("B"));
```

### 3.2 BorderLayout

컨테이너를 NORTH, SOUTH, EAST, WEST, CENTER의 5개 영역으로 나눈다:

```java
container.setLayout(new BorderLayout());
container.add(new JButton("Top"), BorderLayout.NORTH);
container.add(new JButton("Center"), BorderLayout.CENTER);
```

### 3.3 GridLayout

컴포넌트를 행과 열의 격자 형태로 배치한다:

```java
container.setLayout(new GridLayout(3, 2)); // 3행, 2열
```

| 컨테이너 | 기본 배치 관리자 |
|:----------|:--------------|
| JFrame, JDialog | BorderLayout |
| JPanel | FlowLayout |

---

<br>

## 4. 주요 Swing 컴포넌트

| 컴포넌트 | 용도 |
|:----------|:--------|
| `JLabel` | 텍스트 또는 이미지 표시 |
| `JButton` | 클릭 가능한 버튼 |
| `JTextField` | 한 줄 텍스트 입력 |
| `JTextArea` | 여러 줄 텍스트 입력 |
| `JCheckBox` | 토글 선택/해제 |
| `JRadioButton` | 배타적 선택 (ButtonGroup과 함께 사용) |
| `JComboBox` | 드롭다운 선택 |
| `JList` | 스크롤 가능한 목록 |
| `JScrollPane` | 모든 컴포넌트에 스크롤바 추가 |
| `JMenuBar`, `JMenu`, `JMenuItem` | 메뉴 시스템 |

---

<br>

## 5. 이벤트 처리

### 5.1 ActionListener

```java
JButton button = new JButton("Click Me");
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked!");
    }
});
```

### 5.2 익명 클래스와 람다

```java
// 람다 (Java 8+) -- 단일 메서드 인터페이스에 대한 간결한 문법
button.addActionListener(e -> System.out.println("Clicked!"));
```

> **핵심 요점:** 간결한 이벤트 핸들러에는 람다를 사용한다. 핸들러 로직이 복잡하거나 여러 컴포넌트에서 공유되는 경우에는 명명된 클래스를 사용한다.

---

<br>

## 요약

| 개념 | 핵심 요점 |
|:--------|:----------|
| Swing | 경량, 플랫폼 독립적인 Java GUI 툴킷 |
| JFrame | 최상위 윈도우; 커스텀 프레임을 위해 상속하여 사용 |
| Content Pane | GUI 객체가 배치되는 컨테이너 |
| 배치 관리자 | FlowLayout (JPanel 기본), BorderLayout (JFrame 기본), GridLayout |
| 이벤트 처리 | 리스너 인터페이스 구현; 간결함을 위해 람다 사용 |
| 컴포넌트 | JButton, JLabel, JTextField, JTextArea, JComboBox 등 |
