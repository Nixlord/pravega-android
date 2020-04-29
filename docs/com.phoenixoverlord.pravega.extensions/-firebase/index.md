[./](../../index.md) / [com.phoenixoverlord.pravega.extensions](../index.md) / [Firebase](./index.md)

# Firebase

`object Firebase`

### Properties

| Name | Summary |
|---|---|
| [auth](auth.md) | `val auth: FirebaseAuth` |
| [firestore](firestore.md) | `val firestore: FirebaseFirestore` |
| [realtime](realtime.md) | `val realtime: DatabaseReference` |
| [storage](storage.md) | Custom getters so that we don't store a reference to context (FirebaseFirestore.getInstance() has reference to context)`val storage: StorageReference` |
