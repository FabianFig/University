#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// Function to calculate greatest common divisor (GCD) of two numbers
unsigned long gcd(unsigned long a, unsigned long b)
{
  // Body of the function. This function should be implemented recursively
  while (b != 0)
  {
    int tmp = b;
    b = a % b;
    a = tmp;
  }
  return a;
}

// Function to calculate modulo inverse using extended Euclidean algorithm
unsigned long modInverse(unsigned long a, unsigned long m)
{

  // Body of the function
  long mm = m;
  long x = 0, xx = 1;
  long tmp;

  if (m == 1)
  {
    return 0;
  }

  while (a > 1)
  {
    long q = a / m;
    tmp = m;

    m = a % m;
    a = tmp;

    tmp = x;
    x = xx - q * x;
    xx = tmp;
  }

  if (xx < 0)
  {
    xx += mm;
  }
  return xx;
}

// Function to generate keys for RSA encryption
void generateKeys(unsigned long p, unsigned long q, unsigned long *n, unsigned long *e, unsigned long *d)
{

  // Body of the function
  *n = p * q;
  long phiN = (p - 1) * (q - 1);

  *e = 3;
  while (gcd(*e, phiN) != 1)
  {
    (*e)++;
  }

  *d = modInverse(*e, phiN);
}

// Function to encrypt plaintext using RSA
unsigned long encrypt(unsigned long plaintext, unsigned long e, unsigned long n)
{

  // Body of the function
  long result = 1;
  long base = plaintext;

  while (e > 0)
  {
    if ((e % 2) == 1)
    {
      result = (result * base) % n;
    }
    e = e / 2;
    base = (base * base) % n;
  }
  return result;
}

// Function to decrypt ciphertext using RSA
unsigned long decrypt(unsigned long ciphertext, unsigned long d, unsigned long n)
{

  // Body of the function
  long result = 1;
  long base = ciphertext;

  while (d > 0)
  {
    if ((d % 2) == 1)
    {
      result = (result * base) % n;
    }
    d = d / 2;
    base = (base * base) % n;
  }
  return result;
}

int main()
{
  unsigned long p; // prime number, try  3, 5, 7, 11, 13, ...
  unsigned long q; // prime number, try  3, 5, 7, 11, 13, ...

  unsigned long n, e, d;

  printf("Enter p and q: ");
  scanf("%lu %lu", &p, &q);
  // Generate RSA keys
  generateKeys(p, q, &n, &e, &d);

  printf("Public key (e, n): (%lu, %lu)\n", e, n);
  printf("Private key (d, n): (%lu, %lu)\n\n", d, n);

  // Encrypt and decrypt a sample plaintext, which we assume is given as an integer value

  unsigned long plaintext;
  printf("Enter an integer between 0 and n-1 as plain text to be encrypted: ");
  scanf("%lu", &plaintext);
  printf("Original plaintext: %lu\n", plaintext);
  unsigned long ciphertext = encrypt(plaintext, e, n);
  printf("Encrypted ciphertext: %lu\n", ciphertext);
  unsigned long decrypted = decrypt(ciphertext, d, n);
  printf("Decrypted plaintext: %lu\n\n", decrypted);

  return 0;
}
