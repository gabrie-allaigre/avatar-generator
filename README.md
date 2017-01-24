# Avatar Generator

```xml
<dependency>
    <groupId>com.talanlabs</groupId>
    <artifactId>avatar-generator</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Avatar prédéfinis

### Cat

```xml
<dependency>
    <groupId>com.talanlabs</groupId>
    <artifactId>avatar-generator-cat</artifactId>
    <version>1.0.0</version>
</dependency>
```

Images sources : https://framagit.org/Deevad/cat-avatar-generator

**Example 1 :**

```java
Avatar avatar = CatAvatar.newAvatarBuilder().build();

avatar.create(123456L);
```

![image](doc/cat1.png)

**Example 2 :**

```java
Avatar avatar = CatAvatar.newAvatarBuilder()
    .layers(new ShadowLayer(), new RandomColorPaintBackgroundLayer(), new RoundRectMaskLayer())
    .padding(8).margin(8).build();

avatar.create(123456L);
```

![image](doc/cat2.png)

### Smiley

```xml
<dependency>
    <groupId>com.talanlabs</groupId>
    <artifactId>avatar-generator-smiley</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Example 1 :**

```java
Avatar avatar = SmileyAvatar.newAccessoriesAvatarBuilder().build();

avatar.create(123456L);
```

![image](doc/smiley1.png)

**Example 2 :**

```java
Avatar avatar = SmileyAvatar.newEyeMouthAvatarBuilder().build();

avatar.create(123456L);
```

![image](doc/smiley2.png)

**Example 3 :**

```java
Avatar avatar = SmileyAvatar.newGhostAvatarBuilder().build();

avatar.create(123456L);
```

![image](doc/smiley3.png)

**Example 4 :**

```java
Avatar avatar = SmileyAvatar.newDefaultAvatarBuilder().build();

avatar.create(123456L);

```

![image](doc/smiley4.png)

### 8 bit

```xml
<dependency>
    <groupId>com.talanlabs</groupId>
    <artifactId>avatar-generator-8bit</artifactId>
    <version>1.0.0</version>
</dependency>
```

Images sources : https://github.com/o1egl/govatar

**Example 1 :**

```java
Avatar avatar = EightBitAvatar.newMaleAvatarBuilder().build();

avatar.create(123456L);
```

![image](doc/8bitmale.png)

**Example 2 :**

```java
Avatar avatar = EightBitAvatar.newFemaleAvatarBuilder().build();

avatar.create(123456L);
```

![image](doc/8bitfemale.png)