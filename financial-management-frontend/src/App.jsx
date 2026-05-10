import { useEffect, useState } from "react";
import axios from "axios";

function App() {

    const [fullName, setFullName] = useState("");

    const [email, setEmail] = useState("");

    const [password, setPassword] = useState("");

    const [transactions, setTransactions] = useState([]);

    const [summary, setSummary] = useState(null);

    const handleRegister = async () => {

        try {

            const response = await axios.post(
                "http://localhost:8080/api/v1/auth/register",
                {
                    fullName: fullName,
                    email: email,
                    password: password
                }
            );

            console.log(response.data);

            alert("Registration Successful");

        } catch (error) {

            console.log(error);

            alert("Registration Failed");
        }
    };

    const handleLogin = async () => {

        try {

            const response = await axios.post(
                "http://localhost:8080/api/v1/auth/login",
                {
                    email: email,
                    password: password
                }
            );

            localStorage.setItem(
                "token",
                response.data.token
            );

            console.log(response.data.token);

            fetchTransactions();

            fetchSummary();

            alert("Login Successful");

        } catch (error) {

            console.log(error);

            alert("Login Failed");
        }
    };

    const fetchTransactions = async () => {

        try {

            const token = localStorage.getItem("token");

            const response = await axios.get(
                "http://localhost:8080/api/v1/transactions",
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            console.log(response.data);

            setTransactions(response.data.content);

        } catch (error) {

            console.log(error);
        }
    };

    const fetchSummary = async () => {

        try {

            const token = localStorage.getItem("token");

            const response = await axios.get(
                "http://localhost:8080/api/v1/analytics/summary",
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            setSummary(response.data);

        } catch (error) {

            console.log(error);
        }
    };

    const logout = () => {

        localStorage.removeItem("token");

        window.location.reload();
    };

    useEffect(() => {

        const token = localStorage.getItem("token");

        if (token) {

            fetchTransactions();

            fetchSummary();
        }

    }, []);

    return (
        <div className="min-h-screen bg-gray-100 p-8">

            <div className="max-w-4xl mx-auto">

                <h1 className="text-4xl font-bold mb-8">
                    Financial Dashboard
                </h1>

                <div className="bg-white p-6 rounded-xl shadow mb-8">

                    <h2 className="text-2xl font-semibold mb-4">
                        Authentication
                    </h2>

                    <input
                        type="text"
                        placeholder="Enter Full Name"
                        value={fullName}
                        onChange={(e) => setFullName(e.target.value)}
                        className="border p-2 rounded w-full mb-4"
                    />

                    <input
                        type="email"
                        placeholder="Enter Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="border p-2 rounded w-full mb-4"
                    />

                    <input
                        type="password"
                        placeholder="Enter Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="border p-2 rounded w-full mb-4"
                    />

                    <div className="flex gap-4">

                        <button
                            onClick={handleRegister}
                            className="bg-blue-500 text-white px-4 py-2 rounded"
                        >
                            Register
                        </button>

                        <button
                            onClick={handleLogin}
                            className="bg-black text-white px-4 py-2 rounded"
                        >
                            Login
                        </button>

                        <button
                            onClick={logout}
                            className="bg-red-500 text-white px-4 py-2 rounded"
                        >
                            Logout
                        </button>

                    </div>
                </div>

                {
                    summary && (

                        <div className="grid grid-cols-3 gap-4 mb-8">

                            <div className="bg-white p-6 rounded-xl shadow">

                                <h3 className="text-lg font-semibold">
                                    Income
                                </h3>

                                <p className="text-2xl">
                                    ₹{summary.totalIncome}
                                </p>

                            </div>

                            <div className="bg-white p-6 rounded-xl shadow">

                                <h3 className="text-lg font-semibold">
                                    Expense
                                </h3>

                                <p className="text-2xl">
                                    ₹{summary.totalExpense}
                                </p>

                            </div>

                            <div className="bg-white p-6 rounded-xl shadow">

                                <h3 className="text-lg font-semibold">
                                    Balance
                                </h3>

                                <p className="text-2xl">
                                    ₹{summary.balance}
                                </p>

                            </div>

                        </div>
                    )
                }

                <div className="bg-white p-6 rounded-xl shadow">

                    <h2 className="text-2xl font-semibold mb-4">
                        Transactions
                    </h2>

                    {
                        transactions.map((transaction) => (

                            <div
                                key={transaction.id}
                                className="border-b py-3 flex justify-between"
                            >

                                <div>

                                    <p className="font-medium">
                                        {transaction.description}
                                    </p>

                                    <p className="text-sm text-gray-500">
                                        {transaction.type}
                                    </p>

                                </div>

                                <p className="font-bold">
                                    ₹{transaction.amount}
                                </p>

                            </div>
                        ))
                    }

                </div>

            </div>

        </div>
    );
}

export default App;