#include <algorithm>
#include <iostream>
#include <cmath>

using namespace std;
// O(n log(log(n)))
void sieve(int limit) {
    int n = limit + 1;
    bool *is_prime = new bool[n];
    is_prime[0] = is_prime[1] = false;
    fill(is_prime + 2, is_prime + n, true);
    for (int i = 2, end = sqrt(limit); i <= end; i++) {
        if (is_prime[i]) {
            for (int j = i * i; j <= limit; j += i) {
                is_prime[j] = false;
            }
        }
    }
    int count = 0;
    for (int i = 2; i <= limit; i++) {
        if (is_prime[i]) {
            count++;
        }
    }
    cout << count << '\n';
    delete [] is_prime;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    for (int i = 0; i < t; i++) {
        int n;
        cin >> n;
        sieve(n);
    }
}