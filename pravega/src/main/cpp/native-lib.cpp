#include <jni.h>
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <numeric>
#include <thread>
#include <bitset>
#include <cmath>

using std::thread;
using std::vector;
using std::cout;
using std::string;

#define all(v) v.begin(), v.end()
#define step(i, n, delta) for (int i = 0; i < n; i += delta)
#define repeat(i, n) for (int i = 0; i < n; ++i)

vector<float> marshall(JNIEnv* env, jfloatArray array) {
    const int length = env->GetArrayLength(array);

    float *elements = env->GetFloatArrayElements(array, nullptr);
    vector<float> result(length);

    repeat(i, length) {
        result[i] = elements[i];
    }

    env->ReleaseFloatArrayElements(array, elements, 0);

    return result;
}


extern "C"
JNIEXPORT float JNICALL
Java_com_phoenixoverlord_pravega_ml_Transform_arraySum(
        JNIEnv *env,
        jobject thiz,
        jfloatArray values) {
    auto v = marshall(env, values);
    decltype(v)::value_type sum = 0;

    for (auto i : v) {
        sum += i;
        cout << i;
    }
    cout << "\n";
    return sum;
}


template <class T>
int threadedArraySum(const vector<T> &values) {
    int nThread = thread::hardware_concurrency();
    int size = values.size();
    int batchSize = 10;
    int batches = ceil((double)size / (double)batchSize);
    int groups = ceil((double) batches / (double) nThread);

    vector<thread> threads(nThread);
    vector<T> sums(batches, 0);

    repeat(group, groups) {
        repeat(i, nThread) {
            threads[i] = thread([&](T &sum) {
                sum = 0;
                repeat(idx, batchSize) {
                    sum += values[idx];
                }
            }, std::ref(sums[group + i]));
        }

        for (auto &thread: threads) {
            thread.join();
        }
    }

    return std::accumulate(all(sums), 0, std::plus<T>());
}

extern "C" JNIEXPORT jint JNICALL
Java_com_nixlord_myapplication_MainActivity_arraySumThreaded(
        JNIEnv* env,
        jobject /* this */,
        jfloatArray values) {

    auto v = marshall(env, values);
    return threadedArraySum(v);
}