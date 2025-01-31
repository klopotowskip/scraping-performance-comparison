import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("results.csv")

df[df.columns[1]] = df[df.columns[1]].astype(float) / 1000.0
df[df.columns[2]] = df[df.columns[2]].astype(float) / 1000.0

plt.figure(figsize=(10, 6))
plt.plot(df.iloc[:, 0], df.iloc[:, 1], linestyle='-', color='b', label='Selenium')
plt.plot(df.iloc[:, 0], df.iloc[:, 2], linestyle='-', color='r', label='jsoup')

plt.xlabel("# of attempt")
plt.ylabel("Execution time [s]")
plt.title("Time comparison of scraping tools: Selenium vs jsoup")
plt.legend()
plt.grid(True)

mean_A = df.iloc[:, 1].mean()
mean_B = df.iloc[:, 2].mean()
plt.axhline(mean_A, color='b', linestyle=':', label='Mean Time A')
plt.axhline(mean_B, color='r', linestyle=':', label='Mean Time B')

plt.xlim(1, 100)
plt.ylim(0, 10)

# Save the plot to a JPG file
plt.savefig("plot.jpg", format="jpg", dpi=300)
plt.show()