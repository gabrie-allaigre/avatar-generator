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

Exemple 1 :

```java
Avatar avatar = CatAvatar.newAvatarBuilder().build();

avatar.create(123456L);
```

Exemple 2 :

```java
Avatar avatar = CatAvatar.newAvatarBuilder()
    .layers(new ShadowLayer(), new RandomColorPaintBackgroundLayer(), new RoundRectMaskLayer())
    .padding(8).margin(8).build();

avatar.create(123456L);
```

Sources des images : https://framagit.org/Deevad/cat-avatar-generator

### Smiley

```xml
<dependency>
    <groupId>com.talanlabs</groupId>
    <artifactId>avatar-generator-smiley</artifactId>
    <version>1.0.0</version>
</dependency>
```

Exemple 1 :

```java
Avatar avatar = SmileyAvatar.newAccessoriesAvatarBuilder().build();

avatar.create(123456L);
```

```java
Avatar avatar = SmileyAvatar.newEyeMouthAvatarBuilder().build();

avatar.create(123456L);
```

```java
Avatar avatar = SmileyAvatar.newGhostAvatarBuilder().build();

avatar.create(123456L);
```

```java
Avatar avatar = SmileyAvatar.newDefaultAvatarBuilder().build();

avatar.create(123456L);
```