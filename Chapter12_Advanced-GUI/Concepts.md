# Chapter 12 — Advanced GUI

> **Last Updated:** 2026-03-21

---

## Table of Contents

- [1. Swing Overview](#1-swing-overview)
  - [1.1 AWT vs. Swing](#11-awt-vs-swing)
  - [1.2 GUI Class Hierarchy](#12-gui-class-hierarchy)
- [2. JFrame and Content Pane](#2-jframe-and-content-pane)
  - [2.1 Creating a JFrame](#21-creating-a-jframe)
  - [2.2 Subclassing JFrame](#22-subclassing-jframe)
- [3. Layout Managers](#3-layout-managers)
  - [3.1 FlowLayout](#31-flowlayout)
  - [3.2 BorderLayout](#32-borderlayout)
  - [3.3 GridLayout](#33-gridlayout)
- [4. Common Swing Components](#4-common-swing-components)
- [5. Event Handling](#5-event-handling)
  - [5.1 ActionListener](#51-actionlistener)
  - [5.2 Anonymous Classes and Lambdas](#52-anonymous-classes-and-lambdas)
- [Summary](#summary)

---

<br>

## 1. Swing Overview

### 1.1 AWT vs. Swing

| Feature | AWT | Swing |
|:--------|:----|:------|
| Package | `java.awt` | `javax.swing` |
| Components | Heavyweight (OS-native) | Lightweight (Java-rendered) |
| Look & Feel | Platform-dependent | Platform-independent |
| Naming | `Button`, `Label` | `JButton`, `JLabel` |

### 1.2 GUI Class Hierarchy

```
Object -> Component -> Container -> Window -> Frame -> JFrame
                                          -> Dialog -> JDialog
                    -> JComponent -> JLabel, JButton, JTextField, ...
```

---

<br>

## 2. JFrame and Content Pane

### 2.1 Creating a JFrame

```java
JFrame frame = new JFrame("My Window");
frame.setSize(400, 300);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
```

### 2.2 Subclassing JFrame

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

## 3. Layout Managers

### 3.1 FlowLayout

Places components left-to-right, wrapping to the next row when space runs out:

```java
container.setLayout(new FlowLayout());
container.add(new JButton("A"));
container.add(new JButton("B"));
```

### 3.2 BorderLayout

Divides the container into five regions: NORTH, SOUTH, EAST, WEST, CENTER:

```java
container.setLayout(new BorderLayout());
container.add(new JButton("Top"), BorderLayout.NORTH);
container.add(new JButton("Center"), BorderLayout.CENTER);
```

### 3.3 GridLayout

Arranges components in a grid of rows and columns:

```java
container.setLayout(new GridLayout(3, 2)); // 3 rows, 2 columns
```

| Container | Default Layout |
|:----------|:--------------|
| JFrame, JDialog | BorderLayout |
| JPanel | FlowLayout |

---

<br>

## 4. Common Swing Components

| Component | Purpose |
|:----------|:--------|
| `JLabel` | Display text or images |
| `JButton` | Clickable button |
| `JTextField` | Single-line text input |
| `JTextArea` | Multi-line text input |
| `JCheckBox` | Toggle on/off |
| `JRadioButton` | Exclusive selection (with ButtonGroup) |
| `JComboBox` | Drop-down selection |
| `JList` | Scrollable list |
| `JScrollPane` | Adds scrollbars to any component |
| `JMenuBar`, `JMenu`, `JMenuItem` | Menu system |

---

<br>

## 5. Event Handling

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

### 5.2 Anonymous Classes and Lambdas

```java
// Lambda (Java 8+) -- shorter syntax for single-method interfaces
button.addActionListener(e -> System.out.println("Clicked!"));
```

> **Key Point:** Use lambdas for concise event handlers. Use named classes when the handler logic is complex or shared across components.

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| Swing | Lightweight, platform-independent Java GUI toolkit |
| JFrame | Top-level window; subclass it for custom frames |
| Content Pane | Container where GUI objects are placed |
| Layout Managers | FlowLayout (default for JPanel), BorderLayout (default for JFrame), GridLayout |
| Event Handling | Implement listener interfaces; use lambdas for brevity |
| Components | JButton, JLabel, JTextField, JTextArea, JComboBox, etc. |
