#include <algorithm>
#include <iostream>
#include <cmath>

using namespace std;

// O(n log(log(n)))
bool * sieve(int limit) { 
    int n = limit + 1; // increase limit + 1 because c++ starts at 0 
    bool *is_prime = new bool[n]; // Create array of bools
    is_prime[0] = is_prime[1] = false; // Make 0 and 1 false because they can't be prime
    fill(is_prime + 2, is_prime + n, true); // Make all the other values start as true
    for (int i = 2, end = sqrt(limit); i <= end; i++) {
        if (is_prime[i]) { // Check if it is not prime
            for (int j = i * i; j <= limit; j += i) { // Make all multiples false
                is_prime[j] = false;
            }
        }
    }
    return is_prime; // Return that array
    delete [] is_prime; // Free up mem
}

int equation (int a, int p){
    int i = ceil((double)a/p) * p - a;
    if (a <= p){
        i = i + p;
    }
    return i;

}

int main() {
    // Improve performance cause why not
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int s; 
    cin >> s; // Get the number of tests
    for (int t = 0; t < s; t++) { // For each test
        int m, n;
        cin >> m; // Get lower bound
        cin >> n; // Get upper bound

        int n_sqrt = sqrt(n);
        bool *low_primes = sieve(n_sqrt); // Get our low primes
        int len = n-m+1; // Calculate the length of high primes
        bool *high_primes = new bool[len];
        fill(high_primes, high_primes + len + 1, true); // Make all high primes true
        for (int j = 2; j <= n_sqrt; j++) { // For each low prime j
            if (low_primes[j]) {
                int i = equation(m, j);
                for (;i <= len; i+=j) {
                    high_primes[i] = false;
                }
            }
        }
        for (int j = 0; j < len; j++) {
            if (high_primes[j]) {
                if (j+m>=2){
                    cout << j+m << '\n';
                }
            }
        }
        delete [] low_primes;
        delete [] high_primes;
    }
}